import React, { useState, useEffect } from "react";
import "./AddClassPage.css";
import GoBackButton from "../../components/GoBackButton/GoBackButton";
import NavBarService from "../../Services/NavBarService";
import { useNavigate } from "react-router-dom";
import ClassService from "../../Services/ClassService";
import ResponseHandler from "../../components/ResponseHandler/ResponseHandler";
import Header from "../../components/Header/Header";
import NavBarNavigator from "../../components/NavBarNavigator/NavBarNavigator";

function AddClass() {
    const navigate = useNavigate();
    const [teachers, setTeachers] = useState([]);
    const [teacherFromInput, setTeacherFromInput] = useState('');
    const [teacherOfClass, setTeacherOfClass] = useState(null);
    const [error, setError] = useState("");
    const [response, setResponse] = useState('');
    const [className, setClassName] = useState('');

    useEffect(() => {
        const fetchTeachers = async () => {
            try {
                const response = await NavBarService.getAllTeachers();
                setTeachers(response.data);
                console.log(response);
            } catch (err) {
                console.error('Error fetching teachers:', err);
            }
        };
        fetchTeachers();
    }, []);

    const handleSetTeacher = () => {
        const [firstName, lastName, email] = teacherFromInput.split(' ');
        const matchedTeacher = teachers.find(teacher =>
            teacher.firstName === firstName &&
            teacher.lastName === lastName &&
            teacher.email === email
        );

        if (matchedTeacher) {
            setTeacherOfClass(matchedTeacher);
            setTeacherFromInput('');
            setError('');
        } else {
            setTeacherOfClass(null);
            setError("Type correct data!");
        }
    }

    const handleAddClass = async () => {
        let response = '';
        if (className === '') {
            setError("Class name can't be empty!");
            return;
        }
        try {
            response = await ClassService.addClass(className, teacherOfClass);
            setError('');
            setResponse(response.data);
        } catch (err) {
            if (err.response) {
                if (err.response.status === 409) {
                    console.error("Conflict: ", err.response.data);
                    setError(err.response.data);
                } else {
                    console.error("Error: ", err.response.data);
                    setError("An error occurred: " + err.response.data.message);
                }
            }
        }
    }

    return (
        <div className="page-container">
            <NavBarNavigator />
            <GoBackButton />
            <Header text="Add class:" />
            <div className="background-container">
                <div className="add-items-data">
                    <span>Class Name:</span>
                    <div className="input-container">
                        <input
                            className="input"
                            type="text"
                            placeholder="Type class name"
                            value={className}
                            onChange={(e) => setClassName(e.target.value)}
                        />
                    </div>
                    <div className="current-class-teacher-container">
                        <p className="p-header">Current class teacher:</p>
                        <p><b>{teacherOfClass === null ? "You didn't select teacher yet!" : `${teacherOfClass.firstName} ${teacherOfClass.lastName} ${teacherOfClass.email}`}</b></p>
                    </div>
                    <span>New Teacher:</span>
                    <div className="class-teacher-container">
                        <input
                            className="input"
                            type="text"
                            placeholder="Type class teacher"
                            value={teacherFromInput}
                            onChange={(e) => setTeacherFromInput(e.target.value)}
                            list="teachers"
                        />
                        <datalist id="teachers">
                            {teachers.slice(0, 5).map((teacher) => (
                                <option key={teacher.id} value={`${teacher.firstName} ${teacher.lastName} ${teacher.email}`} />
                            ))}
                        </datalist>
                        <button
                            className="confirm-button"
                            onClick={handleSetTeacher}
                        >
                            Set
                        </button>
                    </div>
                </div>
                <ResponseHandler response={response} error={error} setResponse={setResponse} setError={setError} />
                <button
                    className="confirm-button"
                    onClick={handleAddClass}
                >
                    Add Class
                </button>
            </div>
        </div>
    )
}

export default AddClass;
