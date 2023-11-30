const body = document.querySelector("body");
const modeToggle = body.querySelector(".mode-toggle");
const sidebar = body.querySelector("nav");
const sidebarToggle = body.querySelector(".sidebar-toggle");

let getMode = localStorage.getItem("mode");
if (getMode && getMode === "dark") {
  body.classList.toggle("dark");
}

let getStatus = localStorage.getItem("status");
if (getStatus && getStatus === "close") {
  sidebar.classList.toggle("close");
}

modeToggle.addEventListener("click", () => {
  body.classList.toggle("dark");
  if (body.classList.contains("dark")) {
    localStorage.setItem("mode", "dark");
  } else {
    localStorage.setItem("mode", "light");
  }
});

sidebarToggle.addEventListener("click", () => {
  sidebar.classList.toggle("close");
  if (sidebar.classList.contains("close")) {
    localStorage.setItem("status", "close");
  } else {
    localStorage.setItem("status", "open");
  }
});

import { initializeApp } from "https://www.gstatic.com/firebasejs/10.4.0/firebase-app.js";
import { getAnalytics } from "https://www.gstatic.com/firebasejs/10.4.0/firebase-analytics.js";
import {
  getDatabase,
  ref,
  get,
  set,
} from "https://www.gstatic.com/firebasejs/10.4.0/firebase-database.js";

import {
  getAuth,
  createUserWithEmailAndPassword,
} from "https://www.gstatic.com/firebasejs/10.4.0/firebase-auth.js";

const firebaseConfig = {
  apiKey: "AIzaSyBqxl2obXLR1KglrmOFvkgxEZ0J-EHC0ds",
  authDomain: "digitallabour-8bffd.firebaseapp.com",
  databaseURL: "https://digitallabour-8bffd-default-rtdb.firebaseio.com",
  projectId: "digitallabour-8bffd",
  storageBucket: "digitallabour-8bffd.appspot.com",
  messagingSenderId: "404091137675",
  appId: "1:404091137675:web:ea002bba1991c50656ae8e",
  measurementId: "G-S1KB6F3CB9",
};

const app = initializeApp(firebaseConfig);
const analytics = getAnalytics(app);
const auth = getAuth(app);
const database = getDatabase(app);

const userForm = document.getElementById("form");

function updateNames() {
  var usersRef = ref(database, "Users");

  get(usersRef)
    .then(function (snapshot) {
      var usersData = snapshot.val();
      console.log("Retrieved data:", usersData);

      var namesElements = document.querySelectorAll(".data.names .data-list");
      var emailsElements = document.querySelectorAll(".data.email .data-list");
      Object.keys(usersData).forEach(function (Email, index, Name) {
        var user = usersData[Email];
        if (user && user.Name) {
          namesElements[index].textContent = user.Name;
        } else {
          namesElements[index].textContent = "Name";
        }

        if (Email) {
          emailsElements[index].textContent = Email;
        } else {
          emailsElements[index].textContent = "email";
        }
      });
    })
    .catch(function (error) {
      console.error("Error getting data:", error);
    });
}

updateNames();
