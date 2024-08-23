import React, { useState, useEffect } from "react";
import './SubjectDetails.css';
import { useLocation } from "react-router-dom";
import GoBackButton from "../GoBackButtonComp/GoBackButton";
import NavBarService from "../../Services/NavBarService";
import SubjectService from "../../Services/SubjectService";

function SubjectDetails() {
    const location = useLocation();
    const [allTeachersInSchool, setAllTeachersInSchool] = useState([]);
    const [currentTeachersOfSubject, setCurrentTeachersOfSubject] = useState([]);
    const [newTeachersOfSubject, setNewTeachersOfSubject] = useState([]);
    const [removedTeachersFromSubject, setRemovedTeachersOfSubject] = useState([]);
    const [subject, setSubject] = useState(location.state?.subject || {});
    const [wantChangeData, setWantChangeData] = useState(false);
    const [selectedTeacherData, setSelectedTeacherData] = useState('');
    const [filteredTeachers, setFilteredTeachers] = useState([]);
    const [error, setError] = useState('');
    const [response, setResponse] = useState('');

    const fetchData = async () => {
        try {
            const [teachersResponse, allTeachersResponse] = await Promise.all([
                NavBarService.getAllTeachersOfSubject(subject.id),
                NavBarService.getAllTeachers()
            ]);
    
            const fetchedTeachers = Array.isArray(teachersResponse.data) ? teachersResponse.data : [];
            const fetchedAllTeachers = Array.isArray(allTeachersResponse.data) ? allTeachersResponse.data : [];
    
            setCurrentTeachersOfSubject(fetchedTeachers);
            setNewTeachersOfSubject(fetchedTeachers);
            setAllTeachersInSchool(fetchedAllTeachers);
        } catch (err) {
            console.error('Error fetching data:', err);
        }
    };
    
    
    useEffect(() => {
        fetchData();
    }, []);

    const handleEditData = () => {
        setWantChangeData(prev => !prev);
        if(wantChangeData){
            setNewTeachersOfSubject(currentTeachersOfSubject);
            setResponse('');
        }
    };

    const handleRemoveTeacher = (id) => {
        setNewTeachersOfSubject(prev => {
            const teacherToRemove = prev.find(teacher => teacher.id === id);
            if (teacherToRemove) {
                setRemovedTeachersOfSubject(prevRemoved => {
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

    const handleAddTeacher = () => {
        if (!selectedTeacherData) {
            setError("You need to choose a teacher!");
            return;
        }

        const [firstName, lastName, email] = selectedTeacherData.split(' ');

        const foundTeacher = allTeachersInSchool.find(t => 
            t.firstName === firstName &&
            t.lastName === lastName &&
            t.email === email
        );

        if (foundTeacher) {
            const teacherExists = newTeachersOfSubject.some(t => t.id === foundTeacher.id);
            if (!teacherExists) {
                setError('');

                setNewTeachersOfSubject(prev => [...prev, foundTeacher]);

                const teacherWasRemoved = removedTeachersFromSubject.some(t => t.id === foundTeacher.id);
                if (teacherWasRemoved) {
                    setRemovedTeachersOfSubject(prevRemoved => prevRemoved.filter(teacher => teacher.id !== foundTeacher.id));
                }
            } else {
                setError("Teacher was added to the list!");
            }
        } else {
            setError(`Teacher doesn't exist`);
        }
        setSelectedTeacherData(''); // Reset the input field after adding
    };

    const handleTeacherSearch = (e) => {
        const value = e.target.value;
        setSelectedTeacherData(value);
        
        if (value) {
            const filtered = allTeachersInSchool.filter(teacher =>
                teacher.firstName.toLowerCase().includes(value.toLowerCase()) || 
                teacher.lastName.toLowerCase().includes(value.toLowerCase())
            );
            setFilteredTeachers(filtered);
        } else {
            setFilteredTeachers(currentTeachersOfSubject);
        }
    };

    const handleUpdateData = async () => {
    
        if (subject.name === '') {
            setError("You need to type a subject name");
            return;
        }
    
        try {
            console.log(subject)
            const response = await SubjectService.editSubjectData(subject,removedTeachersFromSubject, newTeachersOfSubject);
            handleEditData();
            fetchData();
            setResponse(response.data);
        } catch (err) {
            setError(err.message || "An error occurred");
        }
    };

    return (
        <div className="add-subject-container">
            <div className="go-back-button">
                <GoBackButton path='/subjects' />
            </div>
            <div className="add-subject-page"> 
                    {!wantChangeData ? (
                        <div className="subject-details-container">
                            {response && 
                            <div className="response-container">
                                <p>{response}</p>
                            </div>}
                            <div className="subject-details-header">
                                <h1 className="subject-name">Subject: {subject.name}</h1>
                            </div>
                            <div className="teachers-container">
                                {currentTeachersOfSubject.length > 0 ? (
                                    <div>
                                        <h2>Teachers:</h2>
                                        <ul>
                                            {currentTeachersOfSubject.map((teacher, index) => (
                                                <li key={teacher.id} className="user-item">
                                                    {index + 1}.
                                                    First name: <b>{teacher.firstName}</b>&nbsp;
                                                    Last name: <b>{teacher.lastName}</b>&nbsp;
                                                    Email: <b>{teacher.email}</b>&nbsp;
                                                </li>
                                            ))}
                                        </ul>
                                    </div>
                                ) : (
                                    <div className="no-teachers-message">
                                        <p>This subject doesn't have any teachers.</p>
                                        <p>You can add them in edit mode.</p>
                                    </div>
                                )}
                            </div>
                            <div className="edit-data-button-container">
                                <button 
                                    className="edit-data-button"
                                    onClick={handleEditData}>Edit Data
                                </button>
                            </div>
                        </div>
                    ) : (
                        <div className="edit-data-container">
                            <div className="subject-container">
                                <h1>Edit name</h1>
                                <input 
                                    type="text" 
                                    value={subject.name} 
                                    onChange={(e) => setSubject(prevSubject => ({
                                        ...prevSubject,
                                        name: e.target.value.trim()
                                    }))
                                }
                                />
                            </div>
                            {newTeachersOfSubject.length > 0 ? (
                                <div className="teachers-container">
                                    <h1>Teachers of {subject.name}:</h1>
                                    <ul>
                                        {newTeachersOfSubject.map((teacher, index) => (
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
                                </div>
                            ) : (
                                <div className="no-teachers-container">
                                    <p>This subject doesn't have teachers yet.</p>
                                </div>
                            )}
                            
                            <div className="add-teacher-container">
                                <div className="add-teacher-header">
                                    <h1>Add teacher</h1>
                                </div>
                                <div className="add-teacher-input">
                                <input
                                    type="text"
                                    id="teacher"
                                    name="teacher"
                                    list="teachers"
                                    placeholder="Type Teacher"
                                    value={selectedTeacherData}
                                    onChange={handleTeacherSearch}
                                    autoComplete="off"
                                />
                                <datalist id="teachers">
                                    {filteredTeachers.slice(0, 5).map((teacher) => (
                                        <option key={teacher.id} value={`${teacher.firstName} ${teacher.lastName} ${teacher.email}`}/>
                                    ))}
                                </datalist>
                                <button className="add-teacher-button" onClick={handleAddTeacher}>Add</button>
                                </div>
                            </div>
                            {error && 
                                <div className="error-container">
                                    <p>{error}</p>
                                </div>
                            }
                            <div className="buttons-container">
                                <button className="cancel-edit-button" onClick={handleEditData}>Cancel</button>
                                <button className="update-button" onClick={handleUpdateData}>Update</button>
                            </div>
                            
                        </div>
                    )}
                </div>
        </div>
    );
}

export default SubjectDetails;
