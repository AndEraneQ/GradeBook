import React, { useState } from "react";
import NavBarService from "../../Services/NavBarService";
import GoBackButton from "../GoBackButtonComp/GoBackButton";
import { useEffect } from "react";
import './SubjectsPage.css';
import { useNavigate } from "react-router-dom";

function SubjectsPage(){

    const [subjects,setSubjects] = useState([]);
    const [error, setError] = useState(null);
    const navigate = useNavigate();

    const getSubjects = async () => {
        try {
            const response = await NavBarService.getSubjects();
            setSubjects(response.data);
        } catch (error) {
            console.error('There was an error fetching the subjects!', error);
            setError("Failed to fetch subjects. Please try again later.");
        }
    };

    const handleAddSubject = () => {
        navigate('/add-subject');
    }

    useEffect(() => {
        getSubjects();
    }, []);

    return (
        <div className="subjects-page">
            <GoBackButton path='/admin-homepage'/>
            <div className="subjects-container">
                {error ? (
                    <div className="error-message">{error}</div>
                ) : (
                    <div>
                        <div className="search-and-add-container">
                            <input className="search-input" type="text" placeholder="🔍 Search:"/>
                            <button className="add-subject-button" onClick={handleAddSubject}> Add New Subject</button>
                        </div>
                        <ul>
                            {subjects.map((subject, index) => (
                                <li key={subject.id} className="subject-item">
                                    {index + 1}. {subject.name}
                                </li>
                            ))}
                        </ul>
                    </div>
                )}
            </div>
        </div>
    );
}

export default SubjectsPage;