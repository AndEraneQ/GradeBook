import { useNavigate } from "react-router-dom";

const useNavBarNavigation = () => {
    const navigate = useNavigate();

    const navBarNavigation = (userRole) => {
        switch (userRole) {
            case 'ROLE_TEACHER':
                navigate('/teacher-homepage'); 
                break;
            case 'ROLE_STUDENT':
                navigate('/student-homepage');
                break;
            case 'ROLE_ADMIN':
                navigate('/admin-homepage');
                break;
            default:
                navigate('/');
                break;
        }
    };

    return navBarNavigation;
};

export default useNavBarNavigation;
