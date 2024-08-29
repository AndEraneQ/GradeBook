import React, { useEffect, useState } from "react";
import './AddSubjectPage.css';
import GoBackButton from "../GoBackButtonComp/GoBackButton";
import NavBarService from "../../Services/NavBarService";
import SubjectService from "../../Services/SubjectService";

function AddSubjectPage() {
    const [teachers, setTeachers] = useState([]);
    const [filteredTeachers, setFilteredTeachers] = useState([]);
    const [selectedTeacherData, setSelectedTeacherData] = useState('');
    const [selectedTeachers, setSelectedTeachers] = useState([]);
    const [subject, setSubject] = useState('');
    const [wantAddTeachers, setWantAddTeachers] = useState(false);
    const [error, setError] = useState('');
    const [response,setResponse] = useState();

    function clearMessages(){
        setError('');
        setResponse('');
    }

    useEffect(() => {
        const fetchTeachers = async () => {
            try {
                const response = await NavBarService.getAllTeachers();
                setTeachers(response.data);
                setFilteredTeachers(response.data);
                console.log(response);
            } catch (err) {
                console.error('Error fetching teachers:', err);
            }
        };
        fetchTeachers();
    }, []);

    const handleAddTeacher = () => {
        if (!selectedTeacherData) {
            setError("You need to choose a teacher!");
            return;
        }

        const [firstName, lastName, email] = selectedTeacherData.split(' ');

        const foundTeacher = teachers.find(t => 
            t.firstName === firstName &&
            t.lastName === lastName &&
            t.email === email
        );

        if (foundTeacher) {
            const teacherExists = selectedTeachers.some(t => t.id === foundTeacher.id);
            if(!teacherExists){
            clearMessages()

            setSelectedTeachers(prev => [...prev, foundTeacher]);
            
            } else{
                setError("Teacher was added to the list!");
            }
        } else {
            setError(`Teacher doesn't exist`);
        }
        setSelectedTeacherData(''); // Reset the input field after adding
    };

    const handleRemoveTeacher = (id) => {
        setSelectedTeachers(prev => prev.filter(teacher => teacher.id !== id));
    };

    const toggleAddTeachers = () => {
        clearMessages();
        setWantAddTeachers(prev => !prev);
    };

    const handleTeacherSearch = (e) => {
        const value = e.target.value;
        setSelectedTeacherData(value);
        
        // Filter teachers based on input
        if (value) {
            const filtered = teachers.filter(teacher =>
                teacher.firstName.toLowerCase().includes(value.toLowerCase()) || 
                teacher.lastName.toLowerCase().includes(value.toLowerCase())
            );
            setFilteredTeachers(filtered);
        } else {
            setFilteredTeachers(teachers); // Reset when input is empty
        }
    };

    const handleAddSubject = async () => {
        clearMessages();

        if (!subject) {
            setError("You need to type subject!");
            return;
        }

        if (wantAddTeachers && selectedTeachers.length < 1) {
            setError("You need to add teacher!");
            return;
        }

        try {
            const resp = await SubjectService.addSubject(subject, selectedTeachers);
            console.log(resp.data);
            setSubject('');
        setSelectedTeachers([]);
        setSelectedTeacherData('');
        setResponse(resp.data);
        } catch (error) {
            setError(error.response.data);
        }
    };

    return (
        <div className="add-subject-container">
            <GoBackButton path='/subjects'/>
            <div className="add-subject-page">
                {response && (
                    <div className="correct-message">
                        <label>{response}</label>
                    </div>

                )}
                <h1>New Subject:</h1>
                <input 
                    type="text"
                    placeholder="Type Name:" 
                    value={subject}
                    onChange={(e) => setSubject(e.target.value)}
                /> 
                <div className="checkbox-container">
                    <label>
                        <input
                            type="checkbox" 
                            id="addTeacher" 
                            name="addTeacher" 
                            onChange={toggleAddTeachers}
                        /> 
                        I want to add a teacher
                    </label>
                </div>
                {wantAddTeachers && (
                    <div className="teacher-selection">
                        {selectedTeachers.length > 0 && (
                            <div className="selected-teachers">
                                <h2>Selected Teachers:</h2>
                                <ul>
                                    {selectedTeachers.map((teacher) => (
                                        <li key={teacher.id}>
                                            {teacher.firstName} {teacher.lastName} {teacher.email}
                                            <button className="remove-button" onClick={() => handleRemoveTeacher(teacher.id)}>Remove</button>
                                        </li>
                                    ))}
                                </ul>
                            </div>
                        )}
                        <div className="add-teacher-container">
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
                                {console.log(filteredTeachers)}
                                {filteredTeachers.slice(0, 5).map((teacher) => (
                                    <option key={teacher.id} value={`${teacher.firstName} ${teacher.lastName} ${teacher.email}`}/>
                                ))}
                            </datalist>
                            <button className="add-teacher-button" onClick={handleAddTeacher}>Add</button>
                        </div>
                    </div>
                )}
                {error && (
                    <div className="error-container">
                        <label>{error}</label>
                    </div>
                )}
                <div className="button-container">
                    <button className="add-subject-button" onClick={handleAddSubject}>Add Subject</button>
                </div>
            </div>
        </div>
    );
} 

export default AddSubjectPage;
