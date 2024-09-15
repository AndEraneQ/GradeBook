import React, { useEffect, useState } from "react";
import './StudentGradesPage.css';
import { useLocation, useNavigate } from "react-router-dom";
import GoBackButton from "../../components/GoBackButton/GoBackButton";
import NavBarService from "../../Services/NavBarService";
import GradeService from "../../Services/GradeService";
import { getUser } from "../../utils/userUtils";
import calculateAverage from "../../utils/userUtils";

function StudentGradesPage(){

    const location = useLocation();
    const navigate = useNavigate();
    const user = location.state?.user;
    const [subjects,setSubjects] = useState([]);
    const [grades, setGrades] = useState({});
    const currentUser = getUser();
    console.log(subjects);
    console.log(grades);


    useEffect(() => {
        const fetchData = async () => {
            try {
                const subjectsFromApi = await NavBarService.getSubjects();
                setSubjects(subjectsFromApi.data);
                await fetchGradesForSubjects(subjectsFromApi.data);
            } catch (error) {
                console.error("Error fetching subjects:", error);
            }
        };
        fetchData();
    }, []);

    const fetchGradesForSubjects = async (subjects) => {
        const gradesData = {};

        for (const subject of subjects) {
            try {
                const response = await GradeService.getGradesForStudentBySubject(user.id, subject.id);
                gradesData[subject.id] = response.data;
            } catch (error) {
                console.error(`Error fetching grades for subject ${subject.id}:`, error);
            }
        }
        setGrades(gradesData);
    };

    return (
        <div className="student-grades-page">
            <GoBackButton  path = {currentUser.role==="TEACHER" ? '/my-class' : '/home'}/>
            <div className="student-grades-container">
                <h1>{user.firstName} {user.lastName} Grades</h1>
                    {subjects.map((subject, index) => (
                        <div key={subject.id}>
                            <p>{index + 1}. {subject.name}</p>
                            <div>
                                {grades[subject.id] && grades[subject.id].length > 0 ? (
                                <>
                                Grades:
                                    {grades[subject.id].map((grade, idx) => (
                                        <span key={grade.id} onClick={() => navigate('/grades-details', { state: { grade: grade, user: user } })}>
                                            {grade.value}{idx < grades[subject.id].length - 1 ? ', ' : ''}
                                        </span>
                                    ))}
                                    <span>
                                        {" "}Average: {calculateAverage(grades[subject.id]).average.toFixed(2)} 
                                        (Expected Final Grade: {calculateAverage(grades[subject.id]).expectedGrade})
                                    </span>
                                </>
                                ) : (
                                    <p>No grades available</p>
                                )}
                            </div>
                        </div>
                    ))}
            </div>
        </div>
    );
}

export default StudentGradesPage;