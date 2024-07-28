
  export const getUserDataFromLocalStorage = () => {
    const userDataString = localStorage.getItem('user_data');
    return userDataString ? JSON.parse(userDataString) : null;
  };

  export const getJwtTokenFromLocalStorage = () => {
    const userData = getUserDataFromLocalStorage();
    return userData ? userData.token : null;
  }