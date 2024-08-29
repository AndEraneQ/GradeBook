import React, { useEffect } from "react";
import GoBackButton from "../GoBackButtonComp/GoBackButton";
import { useState } from "react";
import "./ClassesPage.css";
import ClassService from "../../Services/ClassService";
import { useNavigate } from "react-router-dom";

function ClassesPage(){

    const [error,setError] = useState("");
    const [classes,setClasses] = useState([]);
    const navigate = useNavigate();

    const getAllClasses = async () => {
        try{
            const response = await ClassService.getAllClasses();
            setClasses(response.data);
            console.log(response.data);
        }   
        catch(err){
            setError("Failed to load classes, try again later");
        }
    }

    useEffect(() => {
        getAllClasses();
    }, []);

    const handleClassDetails = (classroom) => {
        
    };

    return (
        <div className="classes-page">
            <GoBackButton path='/home'/>
            <div className="classes-container">
                <div className="header-container">
                    <h1>All classes:</h1>
                </div>
                <div className="button-container">
                    <button 
                        className="add-class-button"
                        onClick={() => {navigate("/class-add")}}>
                            Add New Class
                    </button>
                </div>
                <div className="display-classes">
                    <ul>
                        {classes.map((classroom, index) => (
                            <li key={classroom.id} className="classroom-item" onClick={() => handleClassDetails(classroom)}>
                                {index + 1}. {classroom.name}. Class teacher: - 
                                {classroom.teacherDto ? ` ${classroom.teacherDto.firstName} ${classroom.teacherDto.lastName}` : ' None'}
                            </li>
                        ))}
                    </ul>
                </div>
            </div>
        </div>
    );
}


export default ClassesPage;