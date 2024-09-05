import React, { useEffect, useState } from "react";
import './TeacherClassPage.css';
import GoBackButton from "../GoBackButtonComp/GoBackButton";
import { useLocation, useNavigate } from "react-router-dom";
import { getUser } from "../../utils/userUtils";
import ClassService from "../../Services/ClassService";

function TeacherClassPage(){

    const location = useLocation();
    const navigate = useNavigate();
    const user = getUser();
    const [students,setStudents] = useState([]);
    console.log(user);

    useEffect(() => {
        const fetchData = async () => {
            try{
                const response = await ClassService.getStudentLists(user.classId);
                setStudents(response.data);
                console.log(response.data);
            } catch(err){
                console.log(err);
            }
        }
        fetchData();
    }, []);

    return(
        <div className="teacher-class-page">
            <GoBackButton path='/home'/>
            <div className="teacher-class-container">
                <div className="header-container">
                    <h1>{`Class ${user.className}`}</h1>
                </div>
                <div className="students-container">
                    {students.length<1 ? (
                        <p>Your class don't have students!</p>
                    ) : (
                        <>
                        {students.map((student, index) => (
                            <div key={student.id} onClick={() => navigate('/grades', {state: {user: student}})}>
                                {index + 1}. {student.firstName} {student.lastName}
                            </div>
                        ))}
                        </>
                    )}
                </div>
            </div>
        </div>
    );
}

export default TeacherClassPage;