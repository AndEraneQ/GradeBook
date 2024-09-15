import React from "react";
import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom";
import Login from "./pages/LoginComp/Login";
import Homepage from "./pages/HomepageComp/Homepage";
import UserData from "./pages/UserDataComp/UserData";
import SubjectsPage from "./pages/SubjectsComp/SubjectsPage";
import AllUsersPage from "./pages/AllUsersComp/AllUsersPage";
import RegisterPage from "./pages/RegisterComp/RegisterPage";
import AddSubjectPage from "./pages/AddSubjectComp/AddSubjectPage";
import SubjectDetails from "./pages/SubjectDetailsComp/SubjectDetails";
import ClassesPage from "./pages/ClassesComp/ClassesPage";
import ProtectedRoute from "./routes/ProtectRoute/ProtectedRoute";
import AddClass from "./pages/AddClassComp/AddClassPage";
import EditUserDataPage from "./pages/EditUserDataComp/EditUserDataPage";
import ClassDetailsPage from "./pages/ClassDetailsComp/ClassDetailsPage";
import EditClassPage from "./pages/EditClassComp/EditClassPage";
import ManageTracherInClassPage from "./pages/ManageTeachersInClassComp/ManageTeacherInClassPage";
import StudentGradesPage from "./pages/StudentGradesComp/StudentGradesPage";
import ChooseClassToManageGradesPage from "./pages/ChooseClassToManageGradesComp/ChooseClassToManageGradesPage";
import ManageGradesPage from "./pages/ManageGradesComp/ManageGradesPage";
import GradeDetailsPage from "./pages/GradeDetailsComp/GradeDetailsPage";
import AddGradePage from "./pages/AddGradeComp/AddGradePage";
import MailPage from "./pages/MailComp/MailPage";
import MailDetailsPage from "./pages/MailDetailsComp/MailDetailsPage";
import TeacherClassPage from "./pages/TeacherClassComp/TeacherClassPage";

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
                  <Route path="/grades-details" element={<ProtectedRoute element={<GradeDetailsPage />} />} />
                  <Route path="/add-grade" element={<ProtectedRoute element={<AddGradePage />} />} />
                  <Route path="/mail-box" element={<ProtectedRoute element={<MailPage />} />} />
                  <Route path="/mail-details" element={<ProtectedRoute element={<MailDetailsPage />} />} />
                  <Route path="/my-class" element={<ProtectedRoute element={<TeacherClassPage />} />} />
                  <Route path="/" element={<Navigate to="/login" />} />
                  <Route path="*" element={<Navigate to="/home" />} />
              </Routes>
          </div>
      </Router>
  );
}

export default App;
