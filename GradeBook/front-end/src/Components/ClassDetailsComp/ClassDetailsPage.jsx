import React, { useEffect, useState } from "react";
import GoBackButton from "../GoBackButtonComp/GoBackButton";
import { useLocation, useNavigate } from "react-router-dom";
import ClassService from "../../Services/ClassService";

function ClassDetailsPage(){
    const location = useLocation();
    const navigate = useNavigate();
    const classroom = location.state?.class;
    const [students,setStudents] = useState([]);

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

    return (
        <div className="class-details-page">
            <GoBackButton path='/classes'/>
            <div className="class-details-container">
                <div className="class-details-header-container">
                    <h1>Class name: {classroom.name}</h1>
                </div>
                <div className="class-details-class-teacher-container">
                    <h2>Class teacher:</h2>
                    {/* <p>{classroom.teacherDto.firstName} {classroom.teacherDto.lastName} {classroom.teacherDto.email}</p> */}
                </div>
                <div className="class-details-students-container">
                {console.log(students)};
                    {/* {students.map((student, index) => (
                        console.log(student);
                        // <li key={student.id} className="user-item">
                        //     {index + 1}.
                        //     <b>{student.firstName}</b>&nbsp;
                        //     <b>{student.lastName}</b>&nbsp;
                        //     <b>{student.email}</b>&nbsp;                  
                        // </li>                                       
                    ))} */}
                </div>
            </div>
        </div>
    );
}

export default ClassDetailsPage;