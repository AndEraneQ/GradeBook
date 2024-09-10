import React, { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import UserService from "../../Services/UserService";
import NavBarService from "../../Services/NavBarService";
import GoBackButton from "../GoBackButtonComp/GoBackButton";
import './EditClassPage.css';
import ClassService from "../../Services/ClassService";

function EditClassPage(){

    const location = useLocation();
    const [studentInput,setStudentInput] = useState('');
    const [teacherInput,setTeacherInput] = useState('');
    const [classroom, setClassroom] = useState(location.state?.classroom);
    const [newClassroom,setNewClassroom] = useState(location.state?.classroom);
    const [students, setStudents] = useState(location.state?.students || []);
    const [apiStudents,setApiStudents] = useState([]);
    const [apiTeachers,setApiTeachers] = useState([]);
    const [error,setError] = useState('');
    const navigate = useNavigate();

    useEffect(() => {
        const fetchData = async () => {
            const apiStudents = await UserService.getStudents();
            const apiTeachers = await NavBarService.getAllTeachers();
            setApiStudents(apiStudents.data);
            setApiTeachers(apiTeachers.data);
            console.log(apiTeachers.data);
        }
        fetchData();
    }, []);

    const handleClassroomChange = (e) => {
        const { name, value } = e.target;
        setNewClassroom(prevClassroom => ({
            ...prevClassroom,
            [name]: value
        }));
    };

    const handleDeleteStudent = (studentToDelete) => {
        const newStudents = students.filter(student => student.id !== studentToDelete.id);
        setStudents(newStudents);
    };

    const handleChangeTeacher = () => {
        setError('');
        const email = teacherInput.split(' ').pop();
        setTeacherInput('');
        const newTeacher = apiTeachers.find(teacher => teacher.email === email);
        if (newTeacher) {
            setNewClassroom(prevClassroom => ({
                ...prevClassroom,
                teacherDto: newTeacher
            }));
        } else {
            setError("Type correct teacher");
        }
    }

    console.log(students);

    const handleAddStudent = () => {
        const email = studentInput.split(' ').pop();
        setStudentInput('');
        setError('');
        const studentExists = students.some(student => student.email === email);
        if(studentExists){
            setError('This student was already added!')
            return;
        }

        const newStudent = apiStudents.find(student => student.email === email)
        if(newStudent){
            students.push(newStudent);
        } else {
            setError("Type correct student!");
        }
    }

    const handleUpdateClass = async () => {
        let response = '';
        try{
            response = await ClassService.editClassData(newClassroom,students);
            navigate('/class-details', {state: {class: newClassroom}})
        } catch(err){
            console.log(err);
        }
    }

    return (
        <div className="edit-class-page">
            <GoBackButton path='/class-details' state={{class: classroom}}/>
            <div className="edit-class-container">
                <div className="edit-class-header-container">
                    <h1>Edit class:</h1>
                </div>
                <div className="edit-class-name-container">
                    <h2>Class name:</h2>
                    <input
                        type="text"
                        name="name"
                        value={newClassroom.name}
                        onChange={handleClassroomChange}/>
                </div>
                <div className="edit-class-teacher-container">
                    <h2>Current class teacher:</h2>
                    <b>{newClassroom.teacherDto 
                        ? `${newClassroom.teacherDto.firstName} ${newClassroom.teacherDto.lastName} ${newClassroom.teacherDto.email}` 
                        : "Class doesn't have a teacher."
                    }</b>
                    <p>Type new teacher</p>
                    <input
                        type="text"
                        list="teachers"
                        value={teacherInput}
                        onChange={(e) => setTeacherInput(e.target.value)}/>
                    <datalist id="teachers">
                        {apiTeachers.slice(0, 5).map((teacher) => (
                            <option key={teacher.id} value={`${teacher.firstName} ${teacher.lastName} ${teacher.email}`}/>
                        ))}
                    </datalist>
                    <button onClick={handleChangeTeacher}>Change Teacher</button>
                </div>
                <div className="edit-class-students-container">
                    <h2>Current class students:</h2>
                    {students.length === 0 ? (
                        <p>Class dont have students yet</p>
                    ) : (
                        <ul>
                            {students.map((student, index) => (
                                <li key={student.id} className="user-item">
                                    {index + 1}.
                                    First name: <b> { student.firstName}</b>&nbsp;
                                    Last name: <b>{ student.lastName}</b>  &nbsp;
                                    Email: <b>{ student.email}</b> &nbsp;
                                    <button 
                                        className="delete-student-button"
                                        onClick={() => handleDeleteStudent(student)}>
                                            Delete
                                    </button>
                                </li>
                            ))}
                        </ul>
                    )}
                    <p>Add students</p>
                    <input
                        type="text"
                        list="students"
                        value={studentInput}
                        onChange={(e) => setStudentInput(e.target.value)}/>
                    <datalist id="students">
                        {apiStudents.slice(0, 5).map((student) => (
                            <option key={student.id} value={`${student.firstName} ${student.lastName} ${student.email}`}/>
                        ))}
                    </datalist>
                    <button
                        onClick={handleAddStudent}>
                            Add Student
                    </button>
                    {error && (
                        <div className="error-container">
                            <p>{error}</p>
                        </div>
                    )}
                </div>
                <div className="update-data-container">
                    <button
                        onClick={handleUpdateClass}>
                            Update class
                    </button>
                </div>
            </div>
        </div>
    );
}

export default EditClassPage;