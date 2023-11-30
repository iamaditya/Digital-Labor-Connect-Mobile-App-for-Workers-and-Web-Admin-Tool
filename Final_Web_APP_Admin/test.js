const firebaseApp = firebase.initializeApp({
  apiKey: "AIzaSyBqxl2obXLR1KglrmOFvkgxEZ0J-EHC0ds",
  authDomain: "digitallabour-8bffd.firebaseapp.com",
  databaseURL: "https://digitallabour-8bffd-default-rtdb.firebaseio.com",
  projectId: "digitallabour-8bffd",
  storageBucket: "digitallabour-8bffd.appspot.com",
  messagingSenderId: "404091137675",
  appId: "1:404091137675:web:ea002bba1991c50656ae8e",
  measurementId: "G-S1KB6F3CB9",
});
import { initializeApp } from "https://www.gstatic.com/firebasejs/10.4.0/firebase-app.js";
import { getAnalytics } from "https://www.gstatic.com/firebasejs/10.4.0/firebase-analytics.js";
import {
  getAuth,
  signInWithEmailAndPassword,
  createUserWithEmailAndPassword,
} from "https://www.gstatic.com/firebasejs/10.4.0/firebase-auth.js";

const db = firebaseApp.firestore();
const auth = firebaseApp.auth();

export function loginUser() {
  const auth = getAuth();

  const email = document.getElementById("email").value;
  const password = document.getElementById("password").value;

  signInWithEmailAndPassword(auth, email, password)
    .then((userCredential) => {
      const user = userCredential.user;
      alert("Logged in successfully.");
      window.location.href = "home.html";
    })
    .catch((error) => {
      const errorCode = error.code;
      const errorMessage = error.message;
      alert(`Error (${errorCode}): ${errorMessage}`);
    });
}
