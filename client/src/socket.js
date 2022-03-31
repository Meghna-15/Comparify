import SockJS from 'sockjs-client';
import Stomp from 'stompjs';
import { toast } from "react-toastify";
import store from "./store"


let stompClient = null;

export const openSocket = () => {
  const sock = new SockJS('http://localhost:9001/api/socket');
  stompClient = Stomp.over(sock);

  const headers = { "Authorization": "Bearer " + localStorage.getItem("auth-token") }

  stompClient.connect(headers, (frame) => {
    stompClient.subscribe('/topic/new-alert', function (alert) {
      if(new Set(alert.receiverIds).has(store.getState().user.role.id)){
        toast.success(alert.body);
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



