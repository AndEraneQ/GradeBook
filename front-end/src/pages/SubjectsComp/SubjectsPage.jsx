import React, { useState } from "react";
import NavBarService from "../../Services/NavBarService";
import GoBackButton from "../../components/GoBackButton/GoBackButton";
import { useEffect } from "react";
import './SubjectsPage.css';
import { useNavigate } from "react-router-dom";
import { useLocation } from "react-router-dom";
import NavBarNavigator from "../../components/NavBarNavigator/NavBarNavigator";
import Header from "../../components/Header/Header";

function SubjectsPage(){
    const location = useLocation();
    const [allSubjects,setAllSubjects] = useState([]);
    const [filteredSubjects,setFilteredSubjects] = useState([]);
    const [error, setError] = useState(null);
    const [deletedSubjectMessage, setDeletedSubjectMessage] = useState(location.state?.responseData || null);
    const navigate = useNavigate();
    const getSubjects = async () => {
        try {
            const response = await NavBarService.getSubjects();
            setAllSubjects(response.data);
            setFilteredSubjects(response.data);
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

    const handleFilterSubject = (e) => {
        const newSubjects = allSubjects.filter(subject =>
            subject.name.toLowerCase().startsWith(e.target.value.toLowerCase())
        )
        setFilteredSubjects(newSubjects);
    }

    return (
        <div className="page-container">
            <NavBarNavigator/>
            <Header text="All Subjects:"/>
            <div className="background-container">
                {error && (
                    <div className="error-message">{error}</div>
                )}
                {deletedSubjectMessage && (
                    <div className="response-message">
                        {deletedSubjectMessage}
                    </div>
                )}
                        <div className="search-and-add-container">
                            <input 
                                className="search-input" 
                                type="text" 
                                placeholder="🔍 Search:"
                                onChange={handleFilterSubject}/>
                            <button 
                                className="confirm-button" 
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
                        <div className="display-items">
                        <ul>
                            {filteredSubjects.slice(0,8).map((subject, index) => (
                                <li key={subject.id} className="list-item" onClick={() => handleSubjectDetails(subject)}>
                                    {index + 1}. <b>Subject Name: </b>{subject.name}
                                </li>
                            ))}
                        </ul>
                        </div>
                    </div>
            </div>
    );
}

export default SubjectsPage;