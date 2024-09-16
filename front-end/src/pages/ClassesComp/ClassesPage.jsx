import React, { useEffect } from "react";
import GoBackButton from "../../components/GoBackButton/GoBackButton";
import { useState } from "react";
import "./ClassesPage.css";
import ClassService from "../../Services/ClassService";
import { useLocation, useNavigate } from "react-router-dom";
import NavBarNavigator from "../../components/NavBarNavigator/NavBarNavigator";
import Header from "../../components/Header/Header";
import ResponseHandler from "../../components/ResponseHandler/ResponseHandler";

function ClassesPage(){
    const location = useLocation();
    const [error,setError] = useState("");
    const [classes,setClasses] = useState([]);
    const [response,setResponse] = useState(location.state?.response || "");
    const [information,setInformation] = useState("");
    const navigate = useNavigate();

    const getAllClasses = async () => {
        try {
            const response = await ClassService.getAllClasses();
            response.data.length > 0 ? 
                setClasses(response.data) : setInformation("You have no classes. Click above to add a new one!");
        } catch (err) {
            setError("Failed to load classes, try again later.");
            console.error(err);
        }
    };

    const handleAddNewClass = () => {
        window.history.replaceState(null, '', window.location.pathname);
        navigate("/class-add");
    };

    useEffect(() => {
        getAllClasses();
    }, []);

    return (
        <div className="page-container">
            <NavBarNavigator/>
            <Header text="All classes:"/>
            <div className="background-container">
                
                <div className="button-container">
                    <button 
                        className="confirm-button"
                        onClick={handleAddNewClass}>
                            Add New Class
                    </button>
                </div>             
                <ResponseHandler response={response} error={error} setError={setError} setResponse={setResponse}/>   
                {information ? (
                    <div className="information-container">
                        <p>{information}</p>
                    </div>
                ) : (
                <div className="display-items">
                    <ul className="single-item">
                        {classes.map((classroom, index) => (
                            <li key={classroom.id} className="list-item" onClick={() => navigate('/class-details', {state: {class: classroom}})}>
                                {index + 1}.  <b>Name:</b> {classroom.name}. <b>Class teacher:</b>
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