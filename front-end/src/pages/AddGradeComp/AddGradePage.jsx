import React, { useState } from "react";
import "./AddGradePage.css";
import { useLocation } from "react-router-dom";
import GoBackButton from "../../components/GoBackButton/GoBackButton";
import GradeService from "../../Services/GradeService";

function AddGradePage() {
    const location = useLocation();
    const subjectAndClass = location.state?.subjectAndClass;
    const students = location.state?.students;
    
    const [selectedStudentId, setSelectedStudentId] = useState(students[0]?.id || "");
    const [gradeValue, setGradeValue] = useState("");
    const [description, setDescription] = useState("");

    const handleStudentChange = (e) => {
        setSelectedStudentId(e.target.value);
    };

    const handleGradeChange = (e) => {
        setGradeValue(e.target.value);
    };

    const handleDescriptionChange = (e) => {
        setDescription(e.target.value);
    };

    const handleSave = async () => {

    if(gradeValue==='' || description===''){
        return;
    }

    const request = {
        studentId: selectedStudentId,
        value: gradeValue,
        description: description,
        subjectId: subjectAndClass.subjectDto.id,
    }
        

    try{
        const response = await GradeService.addGrade(request);
        console.log(response);
    } catch(err){
        console.log(err);
        }
    }
        

    return (
        <div className="add-grade-page">
            <GoBackButton path='/manage-grades' state={{subjectAndClass: subjectAndClass}}/>
            <div className="add-grade-container">
                <h1>Subject: {subjectAndClass.subjectDto.name}</h1>
                
                <p>Select student:</p>
                <select value={selectedStudentId} onChange={handleStudentChange}>
                    {students.map((student) => (
                        <option key={student.id} value={student.id}>
                            {student.firstName} {student.lastName}
                        </option>
                    ))}
                </select>

                <p>Grade:</p>
                <input 
                    type="number" 
                    value={gradeValue} 
                    onChange={handleGradeChange} 
                    placeholder="Enter grade"
                />

                <p>Description:</p>
                <input 
                    type="text" 
                    value={description} 
                    onChange={handleDescriptionChange} 
                    placeholder="Enter description"
                />

                <button onClick={handleSave}>Save Grade</button>
            </div>
        </div>
    );
}

export default AddGradePage;
