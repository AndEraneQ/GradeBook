import React from "react";
import "./AddClassPage.css";
import GoBackButton from "../GoBackButtonComp/GoBackButton";

function AddClass(){
    return(
        <div className="add-class-page">
            <div className="go-back-button">
                <GoBackButton path='/classes'/>
            </div>
            <div className="add-class-container">
                <div className="header-container">
                    <h1>Add class:</h1>
                </div>
                <div className="add-class-data">
                    <div className="input-container">
                        <input type="text" placeholder="Type class name"/>
                    </div>
                    <div className="current-class-teacher-container">
                        <h2>Current class teacher:</h2>
                    </div>
                    <div className="class-teacher-container">
                        <input type="text" placeholder="Type class teacher"/>
                        <button className="add-button">Add</button>
                    </div>
                    <div className="current-class-students-container">
                        <h2>Students in class:</h2>
                    </div>
                    <div className="class-students-container">
                        <input type="text" placeholder="Type student data"/>
                        <button className="add-button">Add</button>
                    </div>
                </div>
                <div className="buttons-container">                    
                        <button className="cancel">Cancel</button>
                        <button className="add">Add Class</button>                   
                </div>
            </div> 
        </div>
    )
}

export default AddClass;