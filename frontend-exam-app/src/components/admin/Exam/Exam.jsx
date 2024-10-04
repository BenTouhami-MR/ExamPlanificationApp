import Sidnav from "../../utils/Sidenav";
import Box from "@mui/material/Box";
import ExamForm from "./ExamForm";

export default function Exam(){
    return(
          <Box sx={{display:"flex"}}>
              <Sidnav/>
              <Box component="main" sx={{ flexGrow: 1, p: 3 }}>
                    <ExamForm/>
              </Box>
              </Box>
    )
}