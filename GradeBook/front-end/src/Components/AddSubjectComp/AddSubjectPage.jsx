import React from "react";
import './AddSubjectPage.css'
import GoBackButton from "../GoBackButtonComp/GoBackButton";

function AddSubjectPage(){
    return (
        <div className="add-subject-container">
            <GoBackButton path='/subjects'/>
            <div className="add-subject-page">
                <h1>New Subject:</h1>
                <input type="text" placeholder="Type Name:" />
                <div className="checkbox-container">
                    <label>
                        <input type="checkbox" id="addTeacher" name="addTeacher" value="yes"/> 
                        I want to add a teacher
                    </label>
                </div>
                <div className="button-container">
                    <button className="add-subject-button">Add Subject</button>
                </div>
            </div>
        </div>
    );
} 

export default AddSubjectPage;