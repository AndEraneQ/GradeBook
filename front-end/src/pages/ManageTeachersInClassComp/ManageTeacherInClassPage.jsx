import React, { useEffect, useState } from "react";
import './ManageTeacherInClassPage.css';
import { useLocation } from "react-router-dom";
import SubjectService from "../../Services/SubjectService";
import NavBarService from "../../Services/NavBarService";
import ClassService from "../../Services/ClassService";
import GoBackButton from "../../components/GoBackButton/GoBackButton";

function ManageTracherInClassPage(){

    const location = useLocation();
    const classroom = location.state?.classroom;
    console.log(classroom);
    const [subjects,setSubjects] = useState([]);
    const [response,setResponse] = useState('');
    const [teacherSubjectClassConnection,setTeacherSubjectClassConnection] = useState([]);
    const [teacherId,setTeacherId] = useState('');

    const fetchData = async () => {
        const subjectsFromApi = await NavBarService.getSubjects();
        const teacherSubjectClassConnectionFromApi = await NavBarService.getTeacherSubjectClassConnection(classroom.id);
        setTeacherSubjectClassConnection(teacherSubjectClassConnectionFromApi.data);
        setSubjects(subjectsFromApi.data);
    }

    useEffect(() => {
        fetchData();
    },[response]);

    const handleUpdateConnection = async (classId,subjectId,teacherId) => {
        setResponse('');
        let response = '';
        try {
            response = await ClassService.updateTeacherSubjectClassConnection(classId,subjectId,teacherId);
            setResponse(response.data)
            console.log(response);
        } catch(err) {
            console.log(err);
        }
    }

    return (
        <div className="manage-teacher-in-class-page">
            <GoBackButton path='/class-details' state={{class: classroom}}/>
            <div className="manage-teacher-in-class-container">
                <div className="manage-teacher-in-class-header">
                    <h1>Manage teachers in class {classroom.name}</h1>
                </div>
                {response && (
                    <div className="response-container">
                        <p>{response}</p>
                    </div>
                )}
                <div className="edit-teachers-subject-class-container">
                    <ul>
                        {subjects.map((subject, index) => {
        
                        const connections = teacherSubjectClassConnection.filter(
                            connection => connection.subjectId === subject.id
                        );
                        console.log(connections);

        
                        const currentTeachers = connections.map(connection => {
                            const teacher = subject.teachersDto.find(teacher => teacher.id === connection.teacherId);
                            return teacher ? `${teacher.firstName} ${teacher.lastName} ${teacher.email}` : 'Unknown Teacher';
                        });

                            return (
                                <li key={index}>
                                    Subject: {subject.name} 
                                    <br />
                                    Current teacher: {currentTeachers.length > 0 ? currentTeachers.join(', ') : 'No teacher assigned'}
                                    <h2>Select new teacher</h2>
                                        <select 
                                            value={teacherId}
                                            onChange={(e) => setTeacherId(e.target.value)}
                                        >
                                            <option value="">Reset Teacher</option>
                                                {subject.teachersDto.map(teacher => (
                                            <option key={teacher.id} value={teacher.id}>
                                        {`${teacher.firstName} ${teacher.lastName} (${teacher.email})`}
                                            </option>
                                        ))}
                                        </select>
                                        <button onClick={() => handleUpdateConnection(classroom.id,subject.id,teacherId)}>Set new teacher</button>
                                </li>
                            );
                        })}
                    </ul>

                </div>
            </div>
        </div>
    );
}

export default ManageTracherInClassPage;