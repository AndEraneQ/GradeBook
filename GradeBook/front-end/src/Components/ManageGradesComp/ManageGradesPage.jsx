import React, { useEffect, useState } from "react";
import './ManageGradesPage.css';
import { useLocation } from "react-router-dom";
import GoBackButton from "../GoBackButtonComp/GoBackButton";
import ClassService from "../../Services/ClassService";

function ManageGradesPage() {

    const location = useLocation();
    const teacher = location.state?.teacher;
    const subjectAndClass = location.state?.subjectAndClass;
    const [students,setStudents] = useState([]);

    useEffect(() => {
        const fetchData = async () => {
            const studentsFromApi = await ClassService.getStudentLists(subjectAndClass.classroomDto.id);
            setStudents(studentsFromApi.data)
        }
        fetchData();
    }, [])

    console.log(students);
    console.log(teacher);

    return(
        <div className="manage-grades-page">
            <GoBackButton path='/choose-class'/>
            <div className="manage-grades-container">
                <div className="manage-grades-header">
                    <h1>{subjectAndClass.subjectDto.name} {subjectAndClass.classroomDto.name}</h1>
                </div>
                <div className="students-container">
                    {students.length === 0 ? (
                        <p>Class doesn't have any students!</p>
                    ) : (
                        <div>
                            <p>Students data</p>
                            {students.map((student, index) => (
                                <div key={index}>
                                    {index+1}. {student.firstName} {student.lastName} {student.email}
                                </div>
                            ))}
                        </div>
                    )}
                </div>
            </div>
        </div>
    );
}

export default ManageGradesPage;