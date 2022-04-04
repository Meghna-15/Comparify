import React, { useEffect, useRef } from "react";
import PropTypes from "prop-types";
import AppBar from "@mui/material/AppBar";
import Box from "@mui/material/Box";
import CssBaseline from "@mui/material/CssBaseline";
import Divider from "@mui/material/Divider";
import Drawer from "@mui/material/Drawer";
import IconButton from "@mui/material/IconButton";
import HomeIcon from "@mui/icons-material/Home";
import List from "@mui/material/List";
import ListItem from "@mui/material/ListItem";
import ListItemIcon from "@mui/material/ListItemIcon";
import AddAlertOutlinedIcon from "@mui/icons-material/AddAlertOutlined";
import AddIcon from "@mui/icons-material/Add";
import ListItemText from "@mui/material/ListItemText";
import MenuIcon from "@mui/icons-material/Menu";
import Toolbar from "@mui/material/Toolbar";
import Typography from "@mui/material/Typography";
import AccountCircleIcon from "@mui/icons-material/AccountCircle";
import LogoutIcon from "@mui/icons-material/Logout";
import AddAlertIcon from "@mui/icons-material/AddAlert";
import { Outlet, useNavigate } from "react-router-dom";
import { getUserRole, logout } from "../../store/thunk/userThunkCreators";
import { useDispatch, useSelector } from "react-redux";
import { isSocketConnected, openSocket } from "../../socket";
import { initServiceWorker } from "../../store/utils/serviceWorkerUtils";
import useStyles from "../../hooks/use-styles";
import Fab from "@mui/material/Fab";
import NotificationTray from "./../notification/NotificationTray";
import CategoryIcon from "@mui/icons-material/Category";
import FeedbackIcon from "@mui/icons-material/Feedback";
import ManageAccountsIcon from "@mui/icons-material/ManageAccounts";
import AddBoxIcon from "@mui/icons-material/AddBox";
import AddBusinessIcon from "@mui/icons-material/AddBusiness";
import BrandingWatermarkIcon from "@mui/icons-material/BrandingWatermark";
import AutoGraphIcon from '@mui/icons-material/AutoGraph';

const style = {
  root: {},
  fabContainer: {
    position: "fixed !important",
    bottom: "50px",
    right: "50px",
  },
};

const drawerWidth = 240;

function Menus(props) {
  const { window } = props;

  const navigate = useNavigate();
  const dispatch = useDispatch();
  const user = useSelector((state) => state.user);

  const userRole = useSelector((state) => state.user.role.role_id);

  //Titles stored for all the menus
  var titles = [
    "Home",
    "Add Item",
    "Analytics",
    "Alerts",
    "Feedback",
    "User Profile",
    "Log out",
  ];
  var titlesAdmin = [
    "Home",
    "Add Brand",
    "Add Store",
    "Add Product",
    "User Management",
    "User Feedback",
    "Log out",
  ];

  const [mobileOpen, setMobileOpen] = React.useState(false);
  const [titlePage, setTitlePage] = React.useState(titles[0]);

  useEffect(() => {
    if (!isSocketConnected()) {
      openSocket();
    }
    dispatch(getUserRole());

    initServiceWorker();
  }, [dispatch]);

  const classes = useStyles(style);
  const notificationRef = useRef();

  const authentication = useSelector((state) => state.authentication);

  const handleDrawerToggle = () => {
    setMobileOpen(!mobileOpen);
  };

  function getIcon(index) {
    if (user.role.role_id === "USER") {
      if (index === 0) return <HomeIcon />;
      else if (index === 1) return <BrandingWatermarkIcon />;
      else if (index === 2) return <AutoGraphIcon />;
      else if (index === 3) return <AddAlertOutlinedIcon />;
      else if (index === 4) return <FeedbackIcon />;
      else if (index === 5) return <AccountCircleIcon />;
      else if (index === 6) return <LogoutIcon />;
      else return <AddIcon />;
    } else {
      if (index === 0) return <HomeIcon />;
      else if (index === 1) return <AddBoxIcon />; //Add brand
      else if (index === 2) return <AddBusinessIcon />; //Add Store
      else if (index === 3) return <CategoryIcon />;
      else if (index === 4) return <ManageAccountsIcon />;
      else if (index === 5) return <FeedbackIcon />;
      else if (index === 6) return <LogoutIcon />;
    }
  }

  function menuList() {
    let list = titlesAdmin.map((text, index) => (
      <ListItem
        button
        key={text + "-admin"}
        style={{ margin: "20px" }}
        onClick={() => {
          menuClicked(index);
        }}
      >
        <ListItemIcon>{getIcon(index)}</ListItemIcon>
        <ListItemText primary={text} />
      </ListItem>
    ));

    if (user.role.role_id === "USER") {
      list = titles.map((text, index) => (
        <ListItem
          button
          key={text + "-user"}
          style={{ margin: "20px" }}
          onClick={() => {
            menuClicked(index);
          }}
        >
          <ListItemIcon>{getIcon(index)}</ListItemIcon>
          <ListItemText primary={text} />
        </ListItem>
      ));
    }
    return list;
  }

  const drawer = (
    <div style={{ height: "100%" }}>
      <Toolbar style={{ background: "#2e4670" }} />
      <Divider />
      <List>{menuList()}</List>
      <Divider />
    </div>
  );

  const container =
    window !== undefined ? () => window().document.body : undefined;

  return (
    <>{ userRole &&  
    <Box sx={{ display: "flex" }}>
      <CssBaseline />
      <AppBar
        position="fixed"
        sx={{
          width: { sm: `calc(100% - ${drawerWidth}px)` },
          ml: { sm: `${drawerWidth}px` },
          background: "#2e4670",
        }}
      >
        <Toolbar>
          <IconButton
            color="inherit"
            aria-label="open drawer"
            edge="start"
            onClick={handleDrawerToggle}
            sx={{ mr: 2, display: { sm: "none" } }}
          >
            <MenuIcon />
          </IconButton>
          <Typography
            variant="h5"
            noWrap
            component="div"
            style={{ marginLeft: "20px", fontWeight: "Bold" }}
          >
            {titlePage}
          </Typography>
        </Toolbar>
      </AppBar>
      <Box
        component="nav"
        sx={{
          width: { sm: drawerWidth },
          flexShrink: { sm: 0 },
        }}
        aria-label="mailbox folders"
      >
        {/* The implementation can be swapped with js to avoid SEO duplication of links. */}
        <Drawer
          container={container}
          variant="temporary"
          open={mobileOpen}
          onClose={handleDrawerToggle}
          ModalProps={{
            keepMounted: true, // Better open performance on mobile.
          }}
          sx={{
            display: { xs: "block", sm: "none" },
            "& .MuiDrawer-paper": {
              boxSizing: "border-box",
              width: drawerWidth,
            },
          }}
        >
          {drawer}
        </Drawer>
        <Drawer
          variant="permanent"
          sx={{
            display: { xs: "none", sm: "block" },
            "& .MuiDrawer-paper": {
              boxSizing: "border-box",
              width: drawerWidth,
            },
          }}
          open
        >
          {drawer}
        </Drawer>
      </Box>
      <Box
        component="main"
        sx={{
          flexGrow: 1,
          p: 3,
          width: { sm: `calc(100% - ${drawerWidth}px)` },
        }}
      >
        <Toolbar />
        {/* <Typography paragraph>
          Lorem ipsum d
        </Typography>
        <Typography paragraph>
          Consequat mauris n
        </Typography> */}
        <Outlet></Outlet>
        {/* <UserProfile></UserProfile> */}
      </Box>
      {authentication && authentication.token && (
        <>
          <NotificationTray ref={notificationRef}></NotificationTray>
          <Fab
            color="primary"
            aria-label="add"
            className={classes.fabContainer}
          >
            <AddAlertIcon
              onClick={() => notificationRef.current.openNotificationTray()}
            />
          </Fab>
        </>
      )}
    </Box>}</>
  );

  function menuClicked(index) {
    if (user.role.role_id === "USER") {
      setTitlePage(titles[index]);
      if (index === 0) {
        navigate("/home")
      } else if (index === 1) {
        navigate("addproduct");
      } else if (index === 2) {
        navigate("analytics");
      } else if (index === 3) {
        navigate("alert");
      } else if (index === 4) {
        navigate("feedback");
      } else if (index === 5) {
        navigate("profile");
      } else {
        dispatch(logout());
      }
    } else {
      setTitlePage(titlesAdmin[index]);
      if (index === 0) {} 
      else if (index === 1) {
        navigate("addbrand");
      } else if (index === 2) {
        navigate("addstore");
      } else if (index === 3) {
        navigate("addproductadmin");
      } else if (index === 4) {
        navigate("users");
      } else if (index === 5) {
      } else if (index === 6) {
        dispatch(logout());
      }
    }
  }
}

Menus.propTypes = {
  /**
   * Injected by the documentation to work in an iframe.
   * You won't need it on your project.
   */
  window: PropTypes.func,
};

export default Menus;
