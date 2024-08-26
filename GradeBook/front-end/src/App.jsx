import React from "react";
import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom";
import Login from "./Components/LoginComp/Login";
import Homepage from "./Components/HomepageComp/Homepage";
import UserData from "./Components/UserDataComp/UserData";
import SubjectsPage from "./Components/SubjectsComp/SubjectsPage";
import AllUsersPage from "./Components/AllUsersComp/AllUsersPage";
import RegisterPage from "./Components/RegisterComp/RegisterPage";
import AddSubjectPage from "./Components/AddSubjectComp/AddSubjectPage";
import SubjectDetails from "./Components/SubjectDetailsComp/SubjectDetails";
import ClassesPage from "./Components/ClassesComp/ClassesPage";
import ProtectedRoute from "./Components/ProtectRoute/ProtectedRoute";
import AddClass from "./Components/AddClassComp/AddClassPage";

function App() {
  return (
      <Router>
          <div className="App">
              <Routes>
                  <Route path="/login" element={<Login />} />
                  <Route path="/home" element={<ProtectedRoute element={<Homepage />} />} />
                  <Route path="/user-data" element={<ProtectedRoute element={<UserData />} />} />
                  <Route path="/subjects" element={<ProtectedRoute element={<SubjectsPage />} />} />
                  <Route path="/users" element={<ProtectedRoute element={<AllUsersPage />} />} />
                  <Route path="/register-user" element={<RegisterPage />} />
                  <Route path="/add-subject" element={<ProtectedRoute element={<AddSubjectPage />} />} />
                  <Route path="/subject-details" element={<ProtectedRoute element={<SubjectDetails />} />} />
                  <Route path="/classes" element={<ProtectedRoute element={<ClassesPage />} />} />
                  <Route path="/class-add" element={<ProtectedRoute element={<AddClass />} />} />
                  <Route path="/" element={<Navigate to="/login" />} />
                  <Route path="*" element={<Navigate to="/home" />} />
              </Routes>
          </div>
      </Router>
  );
}

export default App;
