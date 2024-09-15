import React, { useEffect } from "react";
import GoBackButton from "../../components/GoBackButton/GoBackButton";
import { useState } from "react";
import "./ClassesPage.css";
import ClassService from "../../Services/ClassService";
import { useLocation, useNavigate } from "react-router-dom";

function ClassesPage(){
    const location = useLocation();
    const [error,setError] = useState("");
    const resp = location.state?.response;
    const [classes,setClasses] = useState([]);
    const [information,setInformation] = useState("");
    const navigate = useNavigate();

    const getAllClasses = async () => {
        try {
            const response = await ClassService.getAllClasses();
    
            if (response && response.data && response.data.length > 0) {
                setClasses(response.data);
            } else {
                setInformation("No class added.");
            }
    
            console.log(response.data);
        } catch (err) {
            setError("Failed to load classes, try again later.");
            console.error(err);
        }
    };
    

    useEffect(() => {
        getAllClasses();
    }, []);

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
                {resp && (
                    <div className="resp-container">
                        <p>{resp}</p>
                    </div>
                )}
                {information ? (
                    <div className="information-container">
                        <p>{information}</p>
                    </div>
                ) : (
                <div className="display-classes">
                    <ul>
                        {classes.map((classroom, index) => (
                            <li key={classroom.id} className="classroom-item" onClick={() => navigate('/class-details', {state: {class: classroom}})}>
                                {index + 1}. {classroom.name}. Class teacher: - 
                                {classroom.teacherDto ? ` ${classroom.teacherDto.firstName} ${classroom.teacherDto.lastName}` : ' None'}
                            </li>
                        ))}
                    </ul>
                </div>
                )}
            </div>
        </div>
    );
}


export default ClassesPage;