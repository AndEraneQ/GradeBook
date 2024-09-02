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
import EditUserDataPage from "./Components/EditUserDataComp/EditUserDataPage";
import ClassDetailsPage from "./Components/ClassDetailsComp/ClassDetailsPage";
import EditClassPage from "./Components/EditClassComp/EditClassPage";
import ManageTracherInClassPage from "./Components/ManageTeachersInClassComp/ManageTeacherInClassPage";
import StudentGradesPage from "./Components/StudentGradesComp/StudentGradesPage";
import ChooseClassToManageGradesPage from "./Components/ChooseClassToManageGradesComp/ChooseClassToManageGradesPage";
import ManageGradesPage from "./Components/ManageGradesComp/ManageGradesPage";

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
                  <Route path="/edit-data" element={<ProtectedRoute element={<EditUserDataPage />} />} />
                  <Route path="/class-details" element={<ProtectedRoute element={<ClassDetailsPage />} />} />
                  <Route path="/edit-class" element={<ProtectedRoute element={<EditClassPage />} />} />
                  <Route path="/manage-teachers-in-class" element={<ProtectedRoute element={<ManageTracherInClassPage />} />} />
                  <Route path="/grades" element={<ProtectedRoute element={<StudentGradesPage />} />} />
                  <Route path="/choose-class" element={<ProtectedRoute element={<ChooseClassToManageGradesPage />} />} />
                  <Route path="/manage-grades" element={<ProtectedRoute element={<ManageGradesPage />} />} />
                  <Route path="/" element={<Navigate to="/login" />} />
                  <Route path="*" element={<Navigate to="/home" />} />
              </Routes>
          </div>
      </Router>
  );
}

export default App;
