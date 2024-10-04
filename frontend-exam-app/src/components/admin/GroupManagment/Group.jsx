import Sidnav from "../../utils/Sidenav";
import Box from "@mui/material/Box";
import GroupListTable from "./GroupListTable";

export default function Group(){
    return(

       <Box sx={{display:"flex"}}>
           <Sidnav/>
           <Box component="main" sx={{ flexGrow: 1, p: 3 }}>

           <GroupListTable/>
           </Box>
       </Box>
    )
}