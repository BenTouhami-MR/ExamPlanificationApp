import {Container} from "@mui/material";
import PersonnelTable from "./PersonnelTable";

export default function Personnel(){
    return (
     <>
         <Container sx={{display:"flex",justifyContent:"center",alignItems:"center"}}>
             <PersonnelTable/>
         </Container></>

    )
}