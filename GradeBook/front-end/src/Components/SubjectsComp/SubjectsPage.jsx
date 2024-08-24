import React, { useState } from "react";
import NavBarService from "../../Services/NavBarService";
import GoBackButton from "../GoBackButtonComp/GoBackButton";
import { useEffect } from "react";
import './SubjectsPage.css';
import { useNavigate } from "react-router-dom";
import { useLocation } from "react-router-dom";

function SubjectsPage(){
    const location = useLocation();
    const [subjects,setSubjects] = useState([]);
    const [error, setError] = useState(null);
    const [deletedSubjectMessage, setDeletedSubjectMessage] = useState(location.state?.responseData || null);
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

    const handleSubjectDetails = (subject) => {
        navigate('/subject-details', { state: { subject } });
    }

    return (
        <div className="subjects-page">
            <GoBackButton path='/home'/>
            <div className="subjects-container">
                {error && (
                    <div className="error-message">{error}</div>
                )}
                {deletedSubjectMessage && (
                    <div className="response-message">
                        {deletedSubjectMessage}
                    </div>
                )}
                    <div>
                        <div className="search-and-add-container">
                            <input className="search-input" type="text" placeholder="🔍 Search:"/>
                            <button className="add-subject-button" onClick={handleAddSubject}> Add New Subject</button>
                        </div>
                        <ul>
                            {subjects.map((subject, index) => (
                                <li key={subject.id} className="subject-item" onClick={() => handleSubjectDetails(subject)}>
                                    {index + 1}. {subject.name}
                                </li>
                            ))}
                        </ul>
                    </div>
            </div>
        </div>
    );
}

export default SubjectsPage;