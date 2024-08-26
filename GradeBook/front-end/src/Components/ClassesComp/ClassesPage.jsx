import React, { useEffect } from "react";
import GoBackButton from "../GoBackButtonComp/GoBackButton";
import { useState } from "react";
import "./ClassesPage.css";
import ClassService from "../../Services/ClassService";

function ClassesPage(){

    const [error,setError] = useState("");
    const [classes,setClasses] = useState([]);

    const getAllClasses = async () => {
        try{
            const response = await ClassService.getAllClasses();
            setClasses(response.data);
        }   
        catch(err){
            setError("Failed to load classes, try again later");
        }
    }

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
                <button 
                    className="add-subject-button">
                        Add New Subject
                </button>
                {/* {error && (
                    <div className="error-message">{error}</div>
                )}
                {deletedSubjectMessage && (
                    <div className="response-message">
                        {deletedSubjectMessage}
                    </div>
                )}
                    <div>
                        <div className="header-container">
                            <h1>All subjects:</h1>
                        </div>
                        <div className="search-and-add-container">
                            <input 
                                className="search-input" 
                                type="text" 
                                placeholder="🔍 Search:"
                                onChange={handleFilterSubject}/>
                            <button 
                                className="add-subject-button" 
                                onClick={handleAddSubject}>
                                    Add New Subject
                            </button>
                        </div>
                        {filteredSubjects.length===0 && (
                            <div className="information-container">
                                {allSubjects.length!==0 ? (
                                    <p>Type correct subject name</p>
                                ) : (
                                    <p>There are no subjects, you can add them now.</p>
                                )}
                            </div>
                        )}
                        <ul>
                            {filteredSubjects.slice(0,10).map((subject, index) => (
                                <li key={subject.id} className="subject-item" onClick={() => handleSubjectDetails(subject)}>
                                    {index + 1}. {subject.name}
                                </li>
                            ))}
                        </ul>
                    </div> */}
            </div>
        </div>
    );
}


export default ClassesPage;