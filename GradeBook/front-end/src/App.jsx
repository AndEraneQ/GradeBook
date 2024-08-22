import React from "react";
import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom";
import Login from "./Components/LoginComp/Login";
import AdminHomepage from "./Components/AdminHomepageComp/AdminHomepage";
import UserData from "./Components/UserDataComp/UserData";
import SubjectsPage from "./Components/SubjectsComp/SubjectsPage";
import AllUsersPage from "./Components/AllUsersComp/AllUsersPage";
import RegisterPage from "./Components/RegisterComp/RegisterPage";
import AddSubjectPage from "./Components/AddSubjectComp/AddSubjectPage";
import SubjectDetails from "./Components/SubjectDetailsComp/SubjectDetails";

function App() {
  return (
    <Router>
      <div className="App">
        <Routes>
          <Route path="/login" element={<Login />} />
          <Route path="/admin-homepage" element={<AdminHomepage />} />
          <Route path="/user-data" element={<UserData />} />
          <Route path="/subjects" element={<SubjectsPage />} />
          <Route path="/users" element={<AllUsersPage />} />
          <Route path="/register-user" element={<RegisterPage />} />
          <Route path="/add-subject" element={<AddSubjectPage />} />
          <Route path="/subject-details" element={<SubjectDetails />} />
          <Route path="/" element={<Navigate to="/login" />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
