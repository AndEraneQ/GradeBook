export const getUser = () => {
    const userData = localStorage.getItem("user_data");
    return userData ? JSON.parse(userData) : null;
};

const calculateAverage = (grades) => {
    if (!grades || grades.length === 0) {
        return { average: 0, expectedGrade: 0 }; 
    }

    const sum = grades.reduce((accumulator, grade) => accumulator + grade.value, 0); 
    const average = sum / grades.length; 
    const expectedGrade = Math.round(average);

    return { average, expectedGrade };
};

export default calculateAverage;