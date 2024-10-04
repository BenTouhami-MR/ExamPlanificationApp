import React, {useEffect, useState} from "react";
import styled, { keyframes } from "styled-components";
import backgroundImage from "../assets/Images/img.png";
import { Alert } from "@mui/material";
import Box from "@mui/material/Box";
import { useNavigate } from "react-router-dom";

const Login = () => {
    const navigate = useNavigate();
    const correctEmail = "admin@examPlanification.com";
    const correctPassword = "admin";
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState("");

    const handleLogin = (e) => {
        e.preventDefault();

        if (!email || !password) {
            setError("Please enter both email and password.");
        } else if (email !== correctEmail || password !== correctPassword) {
            setError("Email or password is incorrect.");
        } else {
            // Simulate successful login
            localStorage.setItem("isAuthenticated", "true"); // Store authentication in localStorage
            setError(""); // Clear any existing error
            navigate("/Home"); // Redirect to the Home page
        }
    };

    useEffect(() => {
        localStorage.setItem("isAuthenticated", "false"); // Store authentication in localStorage

    }, []);

    return (
        <LoginContainer>
            <Overlay />
            <Form onSubmit={handleLogin}>
                <Title>Login</Title>
                {error && <><Alert severity="error">{error}</Alert> <Box height={10}></Box></>}
                <Input
                    type="email"
                    placeholder="Email"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                />
                <Input
                    type="password"
                    placeholder="Password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                />
                <LoginButton type="submit">Login</LoginButton>
                <Slogan>Plan Your Exams Efficiently</Slogan>
            </Form>
        </LoginContainer>
    );
};

// Animations
const fadeIn = keyframes`
    0% {
        opacity: 0;
        transform: translateY(-20px);
    }
    100% {
        opacity: 1;
        transform: translateY(0);
    }
`;

// Styled Components
const LoginContainer = styled.div`
    height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
    background-image: url(${backgroundImage});
    background-size: cover;
    background-position: center;
    position: relative;
`;

const Overlay = styled.div`
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    background-color: rgba(0, 0, 0, 0.5); /* Dark overlay for better readability */
`;

const Form = styled.form`
    display: flex;
    flex-direction: column;
    background-color: white;
    padding: 40px;
    border-radius: 10px;
    box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
    width: 320px;
    position: relative;
    z-index: 1; /* Ensure it's above the background */
    animation: ${fadeIn} 1s ease-out;
    opacity: 0.85;
`;

const Title = styled.h2`
    text-align: center;
    margin-bottom: 20px;
    color: #333;
`;

const Input = styled.input`
    padding: 12px;
    margin-bottom: 20px;
    border: 1px solid #ccc;
    border-radius: 5px;
    font-size: 16px;
    transition: all 0.3s ease;
    &:focus {
        outline: none;
        border-color: #4a90e2;
        box-shadow: 0 0 5px rgba(74, 144, 226, 0.5);
    }
`;

const LoginButton = styled.button`
    padding: 12px;
    background-color: #4a90e2;
    color: white;
    border: none;
    border-radius: 5px;
    font-size: 16px;
    cursor: pointer;
    transition: background-color 0.3s, transform 0.2s ease;
    &:hover {
        background-color: #357ab8;
        transform: translateY(-2px);
    }
`;

const Slogan = styled.p`
    margin-top: 20px;
    text-align: center;
    font-size: 14px;
    color: #666;
    font-style: italic;
`;

export default Login;
