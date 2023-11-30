import { initializeApp } from "https://www.gstatic.com/firebasejs/10.4.0/firebase-app.js";
import { getAnalytics } from "https://www.gstatic.com/firebasejs/10.4.0/firebase-analytics.js";
import {
  getDatabase,
  ref,
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

// Initialize Firebase
const app = initializeApp(firebaseConfig);
const analytics = getAnalytics(app);
const auth = getAuth(app);

const userForm = document.getElementById("form");

userForm.addEventListener("submit", (e) => {
  e.preventDefault();
  const title = document.getElementById("title").value;
  const discription = document.getElementById("discription").value;
  const state = document.getElementById("state").value;
  const district = document.getElementById("district").value;
  const number = document.getElementById("number").value;

  const userid = generateUserId();
  adduser(userid, title, discription, state, district, number);
  alert("Successful");
  //window.location.href = "index1.html";
});

function generateUserId() {
  const timestamp = new Date().getTime();
  const randomValue = Math.floor(Math.random() * 1000000);
  return `${timestamp}-${randomValue}`;
}

function adduser(userid, title, discription, state, district, number) {
  const db = getDatabase();
  const reference = ref(db, "jobs/" + state + "/" + district);
  set(reference, {
    Job_Title: title,
    Job_Disc: discription,
    District: district,
    Number: number,
  });
  userForm.reset();
}
