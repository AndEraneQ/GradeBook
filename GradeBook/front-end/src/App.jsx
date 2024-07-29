import React from "react";
import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom";
import Login from "./Components/LoginComp/Login";
import AdminHomepage from "./Components/AdminHomepageComp/AdminHomepage";
import UserData from "./Components/UserDataComp/UserData";

function App() {
  return (
    <Router>
      <div className="App">
        <Routes>
          <Route path="/login" element={<Login />} />
          <Route path="/admin-homepage" element={<AdminHomepage />} />
          <Route path="/user-data" element={<UserData />} />
          <Route path="/" element={<Navigate to="/login" />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
