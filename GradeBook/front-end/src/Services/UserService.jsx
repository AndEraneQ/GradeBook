
export function authHeader() {
    const user = JSON.parse(localStorage.getItem('user_data'));

    if (user && user.token) {
      return { Authorization: 'Bearer ' + user.token };
    } else {
      return {};
    }
}
