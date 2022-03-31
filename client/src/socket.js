import SockJS from 'sockjs-client';
import Stomp from 'stompjs';
import { toast } from "react-toastify";
import store from "./store"


let stompClient = null;

export const openSocket = () => {

  const socketUrl = process.env.REACT_APP_SERVER_BASE_URL + process.env.REACT_APP_SERVER_CONTEXT_PATH + "socket"

  const sock = new SockJS(socketUrl);
  stompClient = Stomp.over(sock);

  const headers = { "Authorization": "Bearer " + localStorage.getItem("auth-token") }

  stompClient.connect(headers, (frame) => {
    stompClient.subscribe('/topic/new-alert', function (payload) {

      const data = JSON.parse(payload.body);

      if(new Set(data.receiverIds).has(store.getState().user.role.id)){
        toast.success(data.message);
      }
    });
  }, (error) => {
    console.log(error)
  });

};


export const closeSocket = () => {
  stompClient.disconnect((response) => {
    console.log("Connection closed!")
  })
}

export const isSocketConnected = () => {
  return (stompClient != null)
}



