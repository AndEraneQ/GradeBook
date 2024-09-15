import React, { useEffect, useState } from "react";
import './ManageGradesPage.css';
import { useLocation, useNavigate } from "react-router-dom";
import GoBackButton from "../../components/GoBackButton/GoBackButton";
import ClassService from "../../Services/ClassService";
import GradeService from "../../Services/GradeService"; 
import calculateAverage from "../../utils/userUtils";

function ManageGradesPage() {
    const location = useLocation();
    const navigate = useNavigate();
    const teacher = location.state?.teacher;
    const subjectAndClass = location.state?.subjectAndClass;
    const [students, setStudents] = useState([]);
    const [grades, setGrades] = useState({}); 
    const [response,setResponse] = useState(location.state?.response);
    console.log(grades);

    useEffect(() => {
        const fetchData = async () => {
            const studentsFromApi = await ClassService.getStudentLists(subjectAndClass.classroomDto.id);
            setStudents(studentsFromApi.data);
            await fetchGradesForStudents(studentsFromApi.data); 
        }
        fetchData();
    }, [subjectAndClass.classroomDto.id]); 

    const fetchGradesForStudents = async (students) => {
        const gradesData = {}; 

        for (const student of students) {
            try {
                const response = await GradeService.getGradesForStudentBySubject(student.id, subjectAndClass.subjectDto.id);
                gradesData[student.id] = response.data; 
            } catch (error) {
                console.error(`Error fetching grades for student ${student.id}:`, error);
            }
        }
        setGrades(gradesData); 
    };

    return (
        <div className="manage-grades-page">
            <GoBackButton path='/choose-class'/>
            <div className="manage-grades-container">
                <div className="manage-grades-header">
                    <h1>{subjectAndClass.subjectDto.name} {subjectAndClass.classroomDto.name}</h1>
                </div>
                {response && (
                    <div className="response-container">
                        <p>{response}</p>
                    </div>
                )}
                <div className="add-grade-button">
                    <button onClick={() => navigate('/add-grade', {state: {students: students, subjectAndClass: subjectAndClass}})}>Add new grade!</button>
                </div>
                <div className="students-container">
                    {students.length === 0 ? (
                        <p>Class doesn't have any students!</p>
                    ) : (
                        <div>
                            <p>Students data</p>
                            {students.map((student, index) => (
                                <div key={student.id}>
                                    {index + 1}. {student.firstName} {student.lastName} {student.email}
                                    <div>
                                        {grades[student.id] && (
                                            <span>
                                                Grades: {grades[student.id].map((grade, idx) => (
                                                    <span key={grade.id} onClick={() => navigate('/grades-details', {state: {grade: grade, subjectAndClass: subjectAndClass}})}>
                                                        {grade.value }{idx < grades[student.id].length - 1 ? ', ' : ''}
                                                    </span> 
                                                ))}
                                                    {(() => {
                                                        const { average, expectedGrade } = calculateAverage(grades[student.id]);
                                                        return (
                                                            <span>
                                                                {" "}Average: {average.toFixed(2)} (Expected Final Grade: {expectedGrade})
                                                            </span>
                                                        );
                                                    })()}
                                            </span>
                                        )}
                                    </div>
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
