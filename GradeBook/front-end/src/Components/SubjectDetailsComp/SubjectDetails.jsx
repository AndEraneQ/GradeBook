import React, { useState } from "react";
import './SubjectDetails.css';
import { useLocation } from "react-router-dom";
import GoBackButton from "../GoBackButtonComp/GoBackButton";
import { useEffect } from "react";
import NavBarService from "../../Services/NavBarService";

function SubjectDetails() {
    const location = useLocation();
    const [teachers, setTeachers] = useState([]);
    const [newTeachers, setNewTeachers] = useState([]);
    const [removedTeachers, setRemovedTeachers] = useState([]);
    const [subject, setSubject] = useState(location.state?.subject || {});
    const [wantChangeData, setWantChangeData] = useState(false);

    // Fetch teachers when the component mounts
    useEffect(() => {
        const fetchTeachers = async () => {
            try {
                const response = await NavBarService.getAllUsersByRole("role_teacher");
                setTeachers(response.data);
                setNewTeachers(response.data); // Initialize newTeachers with the fetched data
            } catch (err) {
                console.error('Error fetching teachers:', err);
            }
        };
        fetchTeachers();
    }, []);

    // Function to toggle the edit mode
    const handleEditData = () => {
        setWantChangeData(prev => !prev);
    };

    const handleRemoveTeacher = (id) => {
        setNewTeachers(prev => {
            const teacherToRemove = prev.find(teacher => teacher.id === id);           
            if (teacherToRemove) {
                setRemovedTeachers(prevRemoved => {
                    if (!prevRemoved.some(teacher => teacher.id === teacherToRemove.id)) {
                        return [...prevRemoved, teacherToRemove];
                    }
                    return prevRemoved;
                });
                return prev.filter(teacher => teacher.id !== id);
            }
            return prev; 
        });
    };

    return (
        <div className="subject-details-page">
            {subject ? (
                <div>
                    <div className="go-back-button">
                        <GoBackButton path='/subjects' />
                    </div>
                    {!wantChangeData ? (
                        <div className="subject-details-container">
                            <div className="subject-details-header">
                                <h1>Subject: {subject.name}</h1>
                            </div>
                            <div className="teachers-container">
                                <p>Teachers:</p>
                                <ul>
                                    {teachers.map((teacher, index) => (
                                        <li key={teacher.id} className="user-item">
                                            {index + 1}.
                                            First name: <b>{teacher.firstName}</b>&nbsp;
                                            Last name: <b>{teacher.lastName}</b>&nbsp;
                                            Email: <b>{teacher.email}</b>&nbsp;
                                        </li>
                                    ))}
                                </ul>
                            </div>
                            <div className="edit-data-button-container">
                                <button 
                                    className="edit-name-button"
                                    onClick={handleEditData}>Edit Data
                                </button>
                            </div>
                        </div>
                    ) : (
                        <div className="edit-data-container">
                            <div className="subject-container">
                                <h1>Edit name: {subject.name}</h1>
                                <input 
                                    type="text" 
                                    value={subject.name} 
                                    onChange={(e) => setSubject(prevSubject => ({
                                        ...prevSubject,
                                        name: e.target.value
                                    }))}
                                />
                            </div>
                            <div className="teachers-container">
                                <h1>Your new teachers:</h1>
                            <ul>
                            {newTeachers.map((teacher, index) => (                                        
                                            <li key={teacher.id} className="user-item">
                                                {index + 1}.
                                                First name: <b>{teacher.firstName}</b>&nbsp;
                                                Last name: <b>{teacher.lastName}</b>&nbsp;
                                                Email: <b>{teacher.email}</b>&nbsp;
                                                <button 
                                                className="remove-button"
                                                onClick={() => handleRemoveTeacher(teacher.id)}>
                                                    remove
                                                </button>
                                            </li>                                       
                                    ))}
                            </ul>
                            <div className="add-teacher-container">
                                <h1>Add teacher:</h1>
                                <input/>
                                <button>Add</button>
                            </div>
                            </div>
                            <div className="buttons-container">
                                <button className="cancel-edit-button" onClick={handleEditData}>Cancel</button>
                                <button className="update-button">Update</button>
                            </div>
                        </div>
                    )}
                </div>
            ) : (
                <div className="subject-details-error">
                    <h1>No subject data available</h1>
                </div>
            )}
        </div>
    );
}

export default SubjectDetails;