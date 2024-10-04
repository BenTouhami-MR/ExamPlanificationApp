import Sidnav from "../../utils/Sidenav";
import Box from "@mui/material/Box";
import {Container} from "@mui/material";
import PersonnelTable from "../PersonnelManagement/PersonnelTable";
import EducationalElementTable from "./EducationalElementTable";
export default function EducationalElement(){
    return (
        <>
        <Container sx={{display:"flex",justifyContent:"center",alignItems:"center"}}>
            <EducationalElementTable/>
        </Container></>
    )
}