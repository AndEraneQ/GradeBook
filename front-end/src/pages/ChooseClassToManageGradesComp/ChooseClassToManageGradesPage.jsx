import React, { useEffect, useState } from "react";
import './ChooseClassToManageGradesPage.css';
import GoBackButton from "../../components/GoBackButton/GoBackButton";
import NavBarService from "../../Services/NavBarService";
import { getUser } from "../../utils/userUtils";
import UserService from "../../Services/UserService";
import { useNavigate } from "react-router-dom";
import NavBarNavigator from "../../components/NavBarNavigator/NavBarNavigator";



function ChooseClassToManageGradesPage(){

    const user = getUser();
    const [teacher,setTeacher] = useState({});
    const [subjectsAndClassesLearnedByTeacher, setSubjectsAndClassesLearnedByTeacher] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        const fetchData = async () => {
            const teacherFromApi = await UserService.getTeacher(user.id);
            setTeacher(teacherFromApi.data);
            const subjectsAndClassesFromApi = await NavBarService.getSubjectsAndClassesOfTeacher(teacherFromApi.data.id)
            setSubjectsAndClassesLearnedByTeacher(subjectsAndClassesFromApi.data);
            
        }
        fetchData();
    },[]);

    console.log(subjectsAndClassesLearnedByTeacher);

    return (
        <div className="choose-class-to-manage-grades-page">
            <NavBarNavigator/>
            <div className="choose-class-to-manage-grades-container">
                <div className="header-container">
                    <h1>Your classes:</h1>
                </div>
                <div className="classes-learned-by-teacher-container">
                    {subjectsAndClassesLearnedByTeacher.length === 0 ? (
                        <p>You don't have any classes!</p>
                    ) : (
                        subjectsAndClassesLearnedByTeacher.map((subjectAndClass, index) => (
                            <div 
                                key={index} 
                                className="subject-and-class-item" 
                                onClick={() => navigate('/manage-grades', {state: {teacher: teacher, 
                                                                                    subjectAndClass: subjectAndClass}})}>
                            <p>{index+1}. {subjectAndClass.subjectDto.name} {subjectAndClass.classroomDto.name}</p>  
                            </div>
                        ))
                    )}
                </div>
            </div>
        </div>
    );
}

export default ChooseClassToManageGradesPage;