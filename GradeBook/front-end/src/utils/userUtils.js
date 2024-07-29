export const getUser = () => {
    const userData = localStorage.getItem("user_data");
    return userData ? JSON.parse(userData) : null;
};