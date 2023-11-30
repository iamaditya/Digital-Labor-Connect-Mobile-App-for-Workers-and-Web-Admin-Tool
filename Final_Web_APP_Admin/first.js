import { initializeApp } from "https://www.gstatic.com/firebasejs/10.4.0/firebase-app.js";
import { getAnalytics } from "https://www.gstatic.com/firebasejs/10.4.0/firebase-analytics.js";
import {
  getAuth,
  createUserWithEmailAndPassword,
} from "https://www.gstatic.com/firebasejs/10.4.0/firebase-auth.js";

// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
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

registerbutton.addEventListener("click", (e) => {
  e.preventDefault(); // Prevent form submission (if it's a form button)

  const email = document.getElementById("email").value;
  const password = document.getElementById("password").value;

  createUserWithEmailAndPassword(auth, email, password)
    .then((userCredential) => {
      // User signed up successfully
      alert("User created successfully.");
    })
    .catch((error) => {
      const errorCode = error.code;
      const errorMessage = error.message;
      alert(`Error (${errorCode}): ${errorMessage}`);
    });
});
