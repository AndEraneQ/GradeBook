import React, { useEffect, useState } from "react";
import GoBackButton from "../GoBackButtonComp/GoBackButton";
import { useLocation, useNavigate } from "react-router-dom";
import ClassService from "../../Services/ClassService";
import './ClassDetailsPage.css';

function ClassDetailsPage(){
    const location = useLocation();
    const navigate = useNavigate();
    const classroom = location.state?.class;
    const [students,setStudents] = useState([]);
    const [error,setError] = useState('');
    const [response,setResponse] = useState('')

    useEffect(()=> {
        const fetchData = async () => {
            try{
            const response = await ClassService.getStudentLists(classroom.id);
            setStudents(response.data);
            } catch (err){
                console.log(err);
            }
        }
        fetchData();
    },[]);

    const handleDeleteClass = async () => {
        let response = '';
        try{
            response = await ClassService.deleteClass(classroom.id);
            navigate('/classes', {state: {response: response.data}})
        } catch(err){
            console.log(err);
        }
    }

    return (
        <div className="class-details-page">
            <GoBackButton path='/classes'/>
            <div className="class-details-container">
                <div className="class-details-header-container">
                    <h1>Class name: {classroom.name}</h1>
                </div>
                <div className="class-details-class-teacher-container">
                    <h2>Class teacher:</h2>
                    <p>
                    {classroom.teacherDto 
                        ? `${classroom.teacherDto.firstName} ${classroom.teacherDto.lastName} ${classroom.teacherDto.email}` 
                        : "Class doesn't have a teacher."
                    }
                    </p>
                </div>
                <div className="class-details-students-container">
                    <h2>Students:</h2>

                    {students.length === 0 ? (<p>No students in the class</p>) 
                    : students.map((student, index) => (
                        <li key={student.id} className="user-item">
                            {index + 1}.&nbsp;
                            <b>{student.firstName}</b>&nbsp;
                            <b>{student.lastName}</b>&nbsp;
                            <b>{student.email}</b>&nbsp;                  
                        </li>                                       
                    ))}
                </div>
                <div className="response-and-error-containers">
                    {response && (
                        <div className="response-container">
                            <p>{response}</p>
                        </div>
                    )}
                    {error && (
                        <div className="error-container">
                            <p>{error}</p>
                        </div>
                    )}
                </div>
                <div className="buttons-container">
                    <button 
                    className="button edit"
                    onClick={() => navigate('/edit-class', {state: {students:students, classroom:classroom}})}>
                        Edit Data
                        </button>
                    <button 
                    className="button manage"
                    onClick={() => navigate('/manage-teachers-in-class', {state: {classroom: classroom}})}>
                        Manage teachers learning in class
                    </button>
                    <button
                    className="button delete"
                    onClick={handleDeleteClass}>
                        Delete Class
                    </button>   
                </div>
            </div>
        </div>
    );
}

export default ClassDetailsPage;