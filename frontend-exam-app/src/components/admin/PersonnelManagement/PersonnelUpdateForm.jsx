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
import {useLocation, useNavigate} from "react-router-dom";


export default function PersonnelUpdateForm() {
    const [open, setOpen] = useState(false);
    const [snackbarOpen, setSnackbarOpen] = useState(false); // Snackbar state
    const [snackbarMessage, setSnackbarMessage] = useState(''); // Snackbar message
    const [snackbarSeverity, setSnackbarSeverity] = useState('success'); // Snackbar severity
    const [errors, setErrors] = useState({});
    const [personnelsInitialData, setPersonnelsInitialData] = useState({}); // For lists like filieres and departments
    const [actionType, setActionType]  =  useState();
    const navigate = useNavigate();


    const location = useLocation();
    const {personnelId,p}  = location.state; // Retrieve state
    const [personnel, setPersonnel] = useState({
        idPersonnel: personnelId,
        nom: p.nom,
        prenom: p.prenom,
        nom_departement: p.nom_departement,
        id_departement: p.id_departement,
        nom_filiere: p.id_departement,
        id_filiere: p.id_filiere,
        typePersonnel: p.typePersonnel

    }); // Store fetched personnel data


    // UseRef hooks for form fields
    const nameRef = useRef('');
    const firstNameRef = useRef('');
    const sectorRef = useRef('');
    const departementRef = useRef('');
    // Fetch personnel data by ID and populate the form
    const fetchPersonnelData = async () => {

        console.log(personnelId)
        try {
            const response = await fetch(`http://localhost:8081/api/admin/personnel/${personnelId}`);
            if (response.ok) {
                const data = await response.json();
                setPersonnel(data)
                if (data) {
                    firstNameRef.current.value = data.nom ;
                    nameRef.current.value = data.prenom ;
                    if (data.typePersonnel===2){
                        departementRef.current.value = data.id_departement ;
                        sectorRef.current.value = data.id_filiere;}
                }; // Store fetched personnel data
            } else {
                setSnackbarMessage("Failed to fetch personnel data");
                setSnackbarSeverity("error");
                setSnackbarOpen(true);
            }
        } catch (error) {
            setSnackbarMessage("An error occurred while fetching personnel data");
            console.log(error);
            setSnackbarSeverity("error");
            setSnackbarOpen(true);
        }
    };

    // Fetch initial data for sectors and departments
    const getInitialData = async () => {
        try {
            const response = await fetch(`http://localhost:8081/api/admin/personnelInitialeData`);
            if (response.ok) {
                setPersonnelsInitialData(await response.json());
            } else {
                console.log("Failed to fetch initial data");
            }
        } catch (error) {
            console.log("An error occurred while fetching initial data");
        }
    };

    useEffect(() => {
        fetchPersonnelData(); // Fetch personnel data on component load
        getInitialData(); // Fetch initial data for sector/department

    }, []);


    // Populate refs with existing personnel data
    useEffect(() => {
        if (personnel) {
            firstNameRef.current.value = personnel.nom || '';
            nameRef.current.value = personnel.prenom || '';
            if(personnel.typePersonnel===2) {
                departementRef.current.value = personnel.id_departement || '';
                sectorRef.current.value = personnel.id_filiere || '';
            }
        }
    }, [personnel]);
    const handleOpenDialog = (action)=>{
     setActionType(action)
     setOpen(true)
    }

    const handleConfirmation = ()=>{

        if (actionType ==='update'){
            setOpen(false)
            handleUpdate()
        }else{

            navigate('/Personnel');
        }
    }


    // Form validation
    const formValidated = () => {
        const newErrors = {};
        if (!firstNameRef.current.value) {
            newErrors.firstName = "First Name is required";
        }
        if (!nameRef.current.value) {
            newErrors.name = "Last Name is required";
        }
        if (personnel?.typePersonnel === 2) { // Only validate for teachers
            if (!sectorRef.current || !sectorRef.current.value) {
                newErrors.sector = "Sector is required";
            }
            if (!departementRef.current || !departementRef.current.value) {
                newErrors.departement = "Department is required";
            }
        }
        setErrors(newErrors);
        return Object.keys(newErrors).length === 0;
    };

    // Update personnel
    const sendPersonnelToBeUpdated = async (personnelData) => {
        try {
            const response = await fetch(`http://localhost:8081/api/admin/updatePersonnel/${personnelId}`, {
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

    // Handle form submission
    const handleUpdate = (e) => {
        if (formValidated()) {

            let personnelData = {
                idPersonnel: personnelId,
                nom: firstNameRef.current.value,
                prenom: nameRef.current.value,
                typePersonnel :  personnel.typePersonnel


            }
            if(personnel.typePersonnel===2){
                personnelData = {...personnelData,...{id_departement: departementRef.current.value ,
                        id_filiere: sectorRef.current.value,}}
            }
            console.log(personnelData)
            sendPersonnelToBeUpdated(personnelData);
            setPersonnel(personnelData);
            console.log(personnel);
            setOpen(false);
        }
    };

    return (
        <>
            <Box height={30}/>

            <Container sx={{display: "flex", justifyContent: "center", alignItems: "center", marginTop: "60px"}}>
                <Paper sx={{padding: '60px'}}>
                    <Typography variant="h5" gutterBottom>
                        {personnel?.typePersonnel === 1 ? "Administrator" : "Teacher"} Form
                    </Typography>
                    <Grid container spacing={4}>
                        <Grid item xs={8} sm={6}>
                            <TextField
                                fullWidth
                                label="First Name"
                                name="First Name"
                                inputRef={firstNameRef}
                                defaultValue= {personnel?.nom}
                                error={!!errors.name}
                                variant="outlined"
                                margin="dense"
                            />
                        </Grid>
                        <Grid item xs={8} sm={6}>
                            <TextField
                                fullWidth
                                inputRef={nameRef}
                                label="Last Name"
                                name="Last Name"
                                defaultValue = {personnel?.prenom}
                                error={!!errors.firstName}
                                variant="outlined"
                                margin="dense"
                            />
                        </Grid>
                        {personnel?.typePersonnel === 2 && (
                            <>
                                <Grid item xs={8} sm={6}>
                                    <FormControl fullWidth margin="dense">
                                        <InputLabel>Sector</InputLabel>
                                        <Select
                                            name="Sector"
                                            inputRef={sectorRef}
                                            label="Sector"
                                            defaultValue={personnel?.id_filiere || ''}
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
                                            inputRef={departementRef}
                                            label="Departement"
                                            defaultValue={personnel?.id_departement || ''}
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
                        )}
                        <Box sx={{display: 'flex', justifyContent: 'flex-end', marginTop: '20px'}}>
                            <Button
                                variant="contained"
                                color="secondary"
                                onClick={() => handleOpenDialog("cancel")}
                                sx={{marginLeft: '1rem', marginTop: "10px"}}
                            >
                                Cancel
                            </Button>
                            <Button
                                variant="contained"
                                color="primary"
                                onClick={() => handleOpenDialog("update")}
                                sx={{marginLeft: '70rem', marginTop: "10px"}}
                            >
                                Update
                            </Button>
                        </Box>
                    </Grid>
                </Paper>
                <DialogAndSnackBar
                    handleConfirmAction={handleConfirmation}
                    actionType="Update"
                    open={open}
                    setOpen={setOpen}
                    snackbarOpen={snackbarOpen}
                    setSnackbarOpen={setSnackbarOpen}
                    snackbarSeverity={snackbarSeverity}
                    snackbarMessage={snackbarMessage}
                />
            </Container>
        </>
    );
}
