import React, { useState } from "react";
import { BrowserRouter, Routes, Route, Navigate, useLocation } from "react-router-dom";
import Home from "./components/admin/Home/Home";
import Personnel from "./components/admin/PersonnelManagement/Personnel";
import EducationalElement from "./components/admin/EducationalELement/EducationalElement";
import Exam from "./components/admin/Exam/Exam";
import Group from "./components/admin/GroupManagment/Group";
import Box from "@mui/material/Box";
import Navbar from "./components/utils/Navbar";
import Sidnav from "./components/utils/Sidenav";
import PersonnelForm from "./components/admin/PersonnelManagement/PersonnelForm";
import PersonnelTable from "./components/admin/PersonnelManagement/PersonnelTable";
import PersonnelUpdateForm from "./components/admin/PersonnelManagement/PersonnelUpdateForm";
import EducationalElementForm from "./components/admin/EducationalELement/EducationalElementForm";
import EducationalElementUpdateForm from "./components/admin/EducationalELement/EducationalElementUpdateForm";
import GroupForm from "./components/admin/GroupManagment/GroupForm";
import Login from "./components/Login";


// Custom ProtectedRoute component
function ProtectedRoute({ children }) {
    const isAuthenticated = localStorage.getItem("isAuthenticated") === "true"; // Check if user is authenticated
    const location = useLocation();




    // If user is not authenticated, redirect to login
    if (!isAuthenticated) {
        return <Navigate to="/login" replace state={{ from: location }} />;
    }

    return children;
}

function Layout() {
    const location = useLocation();

    // Only render layout components (Navbar, Sidenav, etc.) if user is authenticated
    const shouldRenderLayout = location.pathname !== "/" && location.pathname !== "/login";
    const shouldNotRenderHome = location.pathname !=="/Home";

    return (
        <>
            {shouldRenderLayout && <Navbar />}
            {(shouldRenderLayout && shouldNotRenderHome) && <Box height={60} />}
            {(shouldRenderLayout || !shouldNotRenderHome) && (
                <Box sx={{ display: "flex" }}>
                    {shouldRenderLayout && <Sidnav />}
                    <Box component="main" sx={{ flexGrow: 1, p: 3 }}>
                        <Routes>
                            <Route path="/" element={<Navigate to="/login" />} />
                            <Route path="/login" element={<Login  />} />
                            <Route path="Group" element={<ProtectedRoute><Group /></ProtectedRoute>} />
                            <Route path="Group/Form" element={<ProtectedRoute><GroupForm /></ProtectedRoute>} />
                            <Route path="Personnel" element={<ProtectedRoute><PersonnelTable /></ProtectedRoute>} />
                            <Route path="Personnel/Form" element={<ProtectedRoute><PersonnelForm /></ProtectedRoute>} />
                            <Route path="Personnel/UpdatePersonnel" element={<ProtectedRoute><PersonnelUpdateForm /></ProtectedRoute>} />
                            <Route path="EducationalElement" element={<ProtectedRoute><EducationalElement /></ProtectedRoute>} />
                            <Route path="EducationalElement/Form" element={<ProtectedRoute><EducationalElementForm /></ProtectedRoute>} />
                            <Route path="EducationalElement/updateForm" element={<ProtectedRoute><EducationalElementUpdateForm /></ProtectedRoute>} />
                            <Route path="Exam" element={<ProtectedRoute><Exam /></ProtectedRoute>} />
                        </Routes>
                    </Box>
                </Box>
            )}
            {/* Always render the Routes even if layout components are not displayed */}
            {!shouldRenderLayout && (
                <Routes>
                    <Route path="/" element={<Navigate to="/login" />} />
                    <Route path="/login" element={<Login />} />
                </Routes>
            )}
            {!shouldNotRenderHome && (
                <Routes>
                    <Route path="/Home" element={<ProtectedRoute><Home /></ProtectedRoute>} />
                </Routes>
            )}
        </>
    );
}

export default function App() {
    // Example authentication state (replace with your actual logic)

    return (
        <BrowserRouter>
            <Layout />
        </BrowserRouter>
    );
}
