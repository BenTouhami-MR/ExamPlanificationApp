import Box from "@mui/material/Box";
import {
    Button,
    Container,
    FormControl,
    Grid,
    InputLabel,
    Paper,
    Select,
    TextField
} from "@mui/material";
import Typography from "@mui/material/Typography";
import React, {useEffect, useRef, useState} from "react";
import DialogAndSnackBar from "../../utils/DialogAndSnackBar";
import MenuItem from "@mui/material/MenuItem";
import CenteredTabs from "../../utils/Tabs";


export default function PersonnelForm(){
    const [open,setOpen] =useState(false)
    const [snackbarOpen, setSnackbarOpen] = useState(false); // Snackbar state
    const [snackbarMessage, setSnackbarMessage] = useState(''); // Snackbar message
    const [snackbarSeverity, setSnackbarSeverity] = useState('success'); // Snackbar severity
    const [personnelsInitialData,setPersonnelsInitialData] = useState({});
    const [personnelType,setPersonnelType] = useState('');
    const [actionType,setActionType] = useState('');
    const [errors,setErrors] = useState({})



    // UseRef hook

    const nameRef =useRef('')
    const firstNameRef =useRef('');
    const sectorRef =useRef('');
    const departementRef = useRef('')
    // const PERSONNELINITIALVALUES ={
    //     nom:"",
    //     prenom:"",
    //     filiere:"",
    //     departement:"",
    //     typePersonnel:1
    //
    // }
    // const [personnelData,setPersonnelData] = useState(PERSONNELINITIALVALUES);



    const handleClickOpen = () => {
        setOpen(true);
    }

    const formValidated = ()=>{
        const newErrors = {};
        if (!firstNameRef.current.value) {
            newErrors.firstName = "First Name is required";
        }
        if (!nameRef.current.value) {
            newErrors.name = "Last Name is required";
        }
       if(personnelType===2){

           if (!sectorRef?.current.value || sectorRef?.current.value==='') {
               newErrors.sector = "Sector is required";
           }
           if (!departementRef?.current.value || departementRef?.current.value==='') {
               newErrors.departement = "departement is required";
           }
       }


        setErrors(newErrors);

        return Object.keys(newErrors).length === 0;

    }
    const sendPersonnelToBeSave = async (personnelData)=>{
        try {
        const response = await fetch(`http://localhost:8081/api/admin/addpersonnel`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(personnelData)
        });

        if (response.ok) {
            setSnackbarMessage(await response.text());
            setSnackbarSeverity("success");
            setSnackbarOpen(true);
        } else {
            setSnackbarMessage("Failed to update personnel");
            setSnackbarSeverity("error");
            setSnackbarOpen(true);
        }
    } catch (error) {
        setSnackbarMessage("An error occurred while updating personnel");
        setSnackbarSeverity("error");
        setSnackbarOpen(true);
    }
};
    const handleSubmit= ()=> {

        console.log("hello I'm in submit!!")
        if(formValidated()) {
            console.log("Form verified and validated!!")
            console.log(personnelType)
            let personnelData = {
                        nom: firstNameRef.current.value,
                        prenom: nameRef.current.value,
                        typePersonnel :  personnelType



            }
            if(personnelType===2){

                personnelData = {...personnelData,...{id_departement: departementRef.current.value ,
                        id_filiere: sectorRef.current.value,}}
            }
            sendPersonnelToBeSave(personnelData)
            console.log(`personnelData =======> : ${JSON.stringify(personnelData)}`)
        }
            setOpen(false)


    }


    const getInitilData=async ()=>{
    try{
        const response = await fetch(`http://localhost:8081/api/admin/personnelInitialeData`, {
            method: 'GET',
        });
        if (response.ok) {
            // Handle success (e.g., refresh data or show a success message)
            setPersonnelsInitialData( await response.json());
            console.log(personnelsInitialData)

        } else {

           console.log("Failed to connect to fetch Initial data");
        }
    } catch (error) {
        console.log("An error occurred while fetching Initial data");
    }

}

        useEffect(() => {
            getInitilData()
        }, []);

  useEffect(() => {
      firstNameRef.current.value = ''; nameRef.current.value=''
      setErrors({})
  }, [personnelType]);



   return <>

       <Box height={30}/>
       <CenteredTabs onPersnnelType={(type)=> {
           setPersonnelType(type)
       }}/>

       <Container sx={{display:"flex",justifyContent:"center",alignItems:"center",marginTop:"60px"}}>
           <Paper sx={{ padding: '60px' }}>
               <Typography variant="h5" gutterBottom>
                   {personnelType===1 ?"Administrator":"Teacher"} Form
               </Typography>
               <Grid container spacing={4}>
                   <Grid item xs={8} sm={6}>
                       <TextField
                           fullWidth
                           label="First Name"
                           name="First Name"
                           inputRef={nameRef}
                           error={!!errors.name}
                           variant="outlined"
                           margin="dense"
                       />
                   </Grid>
                   <Grid item xs={8} sm={6}>
                       <TextField
                           fullWidth
                           inputRef ={firstNameRef}
                           label="Name"
                           name="Name"
                           error={!!errors.firstName}
                           variant="outlined"
                           margin="dense"
                       />
                   </Grid>
                   {personnelType===2 &&
                     <>
                         <Grid item xs={8} sm={6}>
                             <FormControl fullWidth margin="dense">
                                 <InputLabel>Sector</InputLabel>
                                 <Select
                                     name="Sector"
                                     inputRef ={sectorRef}
                                     label="Sector"
                                 >
                                     {personnelsInitialData.filieresList?.map((filiere) => (
                                         <MenuItem key={filiere.idFil} value={filiere.idFil}>
                                             {filiere.nomFiliere}
                                         </MenuItem>
                                     ))}
                                 </Select>
                                 {errors.sector && <Typography color="error">{errors.sector}</Typography>}

                             </FormControl>
                         </Grid>
                         <Grid item xs={8} sm={6}>
                             <FormControl fullWidth margin="dense">
                                 <InputLabel>Departement</InputLabel>
                                 <Select
                                     name="Departement"
                                     inputRef = {departementRef}
                                     label="Departement"
                                 >
                                     {personnelsInitialData.departementsList?.map((dep) => (
                                         <MenuItem key={dep.idDep} value={dep.idDep}>
                                             {dep.nomDepartement}
                                         </MenuItem>
                                     ))}
                                 </Select>
                                 {errors.departement && <Typography color="error">{errors.departement}</Typography>}
                             </FormControl>
                         </Grid>
                     </>
                   }

                   <Box sx={{ display: 'flex', justifyContent: 'flex-end', marginTop: '20px' }}>
                       <Button
                           variant="contained"
                           color="primary"
                           onClick={() => handleClickOpen()}
                           sx={{ marginLeft: '70rem',marginTop:"10px"}}
                       >
                           Submit
                       </Button>

                   </Box>
               </Grid>
           </Paper>
           <DialogAndSnackBar handleConfirmAction={()=>handleSubmit()}
                              actionType="Submit"
                              open={open}
                              setOpen={setOpen}
                              snackbarOpen={snackbarOpen}
                              setSnackbarOpen={setSnackbarOpen}
                              snackbarSeverity={snackbarSeverity}
                              snackbarMessage={snackbarMessage}

           />


       </Container></>


}