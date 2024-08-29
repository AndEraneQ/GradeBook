import React from "react";
import "./AddClassPage.css";
import GoBackButton from "../GoBackButtonComp/GoBackButton";
import { useState } from "react";
import { useEffect } from "react";
import NavBarService from "../../Services/NavBarService";
import { useNavigate } from "react-router-dom";
import ClassService from "../../Services/ClassService";

function AddClass(){

    const navigate = useNavigate();
    const [teachers,setTeachers] = useState([]);
    const [teacherFromInput, setTeacherFromInput]= useState('');
    const [teacherOfClass,setTeacherOfClass] = useState(null);
    const [error,setError] = useState("");
    const [response,setResponse] = useState('');
    const [className,setClassName] = useState('');

    useEffect(() => {
        const fetchTeachers = async () => {
            try {
                const response = await NavBarService.getAllTeachers();
                setTeachers(response.data);
                console.log(response);
            } catch (err) {
                console.error('Error fetching teachers:', err);
            }
        };
        fetchTeachers();
    }, []);

    const handleSetTeacher = () => {
        const [firstName, lastName, email] = teacherFromInput.split(' ');
        const matchedTeacher = teachers.find(teacher => 
            teacher.firstName === firstName && 
            teacher.lastName === lastName && 
            teacher.email === email
        );
    
        if (matchedTeacher) {
            setTeacherOfClass(matchedTeacher);
            setTeacherFromInput('');
            setError('');
        } else {
            setTeacherOfClass(null);
            setError("Type correct data!");
        }
    }

    const handleAddClass = async () => {
        let response = '';
        try {
            response = await ClassService.addClass(className,teacherOfClass)
            setError('');
            setResponse(response.data);
        } catch (err){
            setResponse('');
            setError(err.response.data.message)
        }
    }

    return(
        <div className="add-class-page">
            <div className="go-back-button">
                <GoBackButton path='/classes'/>
            </div>
            <div className="add-class-container">
                <div className="header-container">
                    <h1>Add class:</h1>
                </div>
                <div className="add-class-data">
                    <div className="input-container">
                        <input 
                        type="text" 
                        placeholder="Type class name"
                        value={className}
                        onChange={(e) => setClassName(e.target.value)}/>
                    </div>
                    <div className="current-class-teacher-container">
                        <h2>Current class teacher:</h2>
                        <p><b>{teacherOfClass === null ? "None" : `${teacherOfClass.firstName} ${teacherOfClass.lastName} ${teacherOfClass.email}`}</b></p>
                    </div>
                    <div className="class-teacher-container">
                        <input 
                        type="text" 
                        placeholder="Type class teacher"
                        value={teacherFromInput}
                        onChange={(e) => setTeacherFromInput(e.target.value)}
                        list="teachers"/>
                        <datalist id="teachers">
                            {teachers.slice(0, 5).map((teacher) => (
                                <option key={teacher.id} value={`${teacher.firstName} ${teacher.lastName} ${teacher.email}`}/>
                            ))}
                        </datalist>
                        <button 
                        className="add-button"
                        onClick={handleSetTeacher}>
                            Set
                        </button>
                    </div>
                </div>
                {error && (
                    <div className="error-container">
                        <p>{error}</p>
                    </div>
                )}
                {response && (
                    <div className="response-container">
                        <p>{response}</p>
                    </div>
                )}
                <div className="buttons-container">                    
                        <button 
                        className="cancel"
                        onClick={() => navigate('/classes')}>
                            Cancel
                        </button>
                        <button 
                        className="add"
                        onClick={handleAddClass}>
                            Add Class
                        </button>                   
                </div>
            </div> 
        </div>
    )
}

export default AddClass;