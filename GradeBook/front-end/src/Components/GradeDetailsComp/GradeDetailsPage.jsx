import React from "react";
import './GradeDetailsPage.css';
import {useLocation, useNavigate } from "react-router-dom";
import GoBackButton from "../GoBackButtonComp/GoBackButton";
import { getUser } from "../../utils/userUtils";
import { useState } from "react";
import GradeService from "../../Services/GradeService";

function GradeDetailsPage() {

    const location = useLocation();
    const navigate = useNavigate();
    const [grade,setGrade] = useState(location.state?.grade);
    const subjectAndClass = location.state?.subjectAndClass;
    const user = getUser();
    const [error,setError] = useState('');
    const [response,setResponse] = useState('');

    console.log(grade);
    
    const handleEditGrade = async () => {
        setResponse('');
        setError('');
        if(grade.value==null || grade.description==null){
            setError("Type all data!");
            return;
        }
        try{
            const response = await GradeService.editGrade(grade);
            console.log(response);
            setResponse(response.data);
        } catch(err){
            console.log(err);
        }
    }

    const handleDeleteGrade = async () => {
        try{
            const response = await GradeService.deleteGrade(grade.id);
            navigate('/manage-grades', {state: {response: response.data, subjectAndClass: subjectAndClass}});
        } catch(err){
            console.log(err);
        }
    }

    return (
        <div className="grade-details-page">
            <GoBackButton path='/manage-grades' state={{subjectAndClass: subjectAndClass}}/>
            {user.role==="STUDENT" ? (
                <div className="grade-details-container">
                <p>Grade: {grade.value} </p>
                <p>Date: {grade.date} </p>
                <p>Description: {grade.description} </p>
            </div>
            ) : (
                
                <div className="grade-details-possible-to-edit-container">
                {response && (
                    <p>{response}</p>
                )}
                {error && (
                    <p>{error}</p>
                )}
                <label>
                    Grade:
                    <input 
                        type="number" 
                        value={grade.value} 
                        onChange={(e) => 
                            setGrade((prevGrade) => ({
                                ...prevGrade,
                                value: e.target.value,
                            }))
                        } 
                    />
                </label>
                <label>
                    Description:
                    <input 
                        type="text" 
                        value={grade.description} 
                        onChange={(e) => 
                            setGrade((prevGrade) => ({
                                ...prevGrade,
                                description: e.target.value,
                            }))
                        } 
                    />
                </label>
                <button onClick={handleEditGrade}>Save</button>
                <button onClick={handleDeleteGrade}>Delete</button>
            </div>
            )}
        </div>
    );
}

export default GradeDetailsPage;