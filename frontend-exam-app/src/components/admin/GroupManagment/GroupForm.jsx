import React, {useEffect, useRef, useState} from 'react';
import {
    Table,
    TableBody,
    TableCell,
    TableHead,
    TableRow,
    Button,
    Grid,
    MenuItem,
    Select,
    Paper,
    Typography,
    CircularProgress,
    Alert,
    InputLabel,
    FormControl,
    Checkbox,
    TextField,
} from '@mui/material';
import { useNavigate} from 'react-router-dom';
import { keyframes } from '@mui/system';
import SendIcon from '@mui/icons-material/Send';
import DialogAndSnackBar from "../../utils/DialogAndSnackBar";
import Box from "@mui/material/Box";
import AddCircleOutlineIcon from '@mui/icons-material/AddCircleOutline';


function GroupForm(){
    const [initialData ,setInitialData]  = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [errors,setErrors] = useState({})
    const [open,setOpen] =useState(false)
    const [snackbarOpen, setSnackbarOpen] = useState(false); // Snackbar state
    const [snackbarMessage, setSnackbarMessage] = useState(''); // Snackbar message
    const [snackbarSeverity, setSnackbarSeverity] = useState('success'); // Snackbar severity
    const [members, setMembers] =useState([]);
    const [typeIsChoosed, setTypeIsChoosed] =useState(false)
    const [selectedMembers, setSelectedMembers] = useState(new Set()); // Store selected member IDs
    const groupTypes = ["General Group","Department Group", "Sector Group"]
    const [types,setTypes] = useState([]);
    const [showChooseBar,setShowChooseBar] = useState(false);
    const [currentType,setCurrentType] =useState("");
    const [currentId,setCurrentId] =useState(0);

    const groupNameRef = useRef()

    const getDepartments = ()=>{
        setTypes(initialData.departementsList)

    }

    const getSection =()=>{
        setTypes(initialData.filieresList)
    }
    const handleGroupTypeChange = (event) => {
        setMembers([])

        if(event.target.value===groupTypes[1]){
            setShowChooseBar(true);
            setCurrentType(groupTypes[1])
             getDepartments()

        }else if (event.target.value===groupTypes[2] ) {
            setShowChooseBar(true);

            setCurrentType(groupTypes[2])
             getSection()

        }else{
            setShowChooseBar(false);
            setCurrentType(groupTypes[0])
            getAllMembers()

        }
        setTypeIsChoosed(false)

    }




    const handleGroupCreation  = ()=>{
        setOpen(true)
    }


    const getAllMembers  = async ()=>{
        console.log("hello")
        try {
            const response = await fetch(`http://localhost:8081/api/admin/groupe/getEnseignants/all/0`, {
                method: 'GET',
            });
            if (response.ok) {
                // Handle success (e.g., refresh data or show a success message)
                const data = await response.json()
                setMembers(data);

                console.log(`data ====> ${data}`)

            } else {
                setSnackbarSeverity("error")
                setSnackbarMessage(`Failed to fetch Members`);
                setOpen(false);
                setSnackbarOpen(true);
            }
        } catch (error) {

            // Handle error
            setSnackbarSeverity("error")
            setSnackbarMessage(`Error occurred during fetch of members!`);
            setSnackbarOpen(true);
            console.error('Error deleting personnel:', error);
        }

    }


    const handleCheckboxChange = (memberId) => {
        setSelectedMembers((prevSelected) => {
            const newSelected = new Set(prevSelected); // Create a new Set from the previous state
            if (newSelected.has(memberId)) {
                newSelected.delete(memberId); // If member is already selected, remove them
            } else {
                newSelected.add(memberId); // If not selected, add the member
            }
            return newSelected; // Return the updated Set
        });
    };


    const createGroup = async (group)=>{
            try {
                // Convert Set to Array if `selectedMembers` is a Set

                const response = await fetch(`http://localhost:8081/api/admin/groupe/create`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(group)
                });

                console.log(group)

                if (response.ok) {

                    const message = await response.text()
                    setSnackbarSeverity("success")
                    setSnackbarMessage(message);
                    setOpen(false);
                    setSnackbarOpen(true);

                } else {
                    setSnackbarSeverity("error")
                    setSnackbarMessage(`Failed to fetch Members`);
                    setOpen(false);
                    setSnackbarOpen(true);
                }
            } catch (error) {
                // Handle error
                setSnackbarSeverity("error")
                setSnackbarMessage(`Error occurred during fetch of members!`);
                setSnackbarOpen(true);
                console.error('Error deleting personnel:', error);
            }finally {
                setSelectedMembers(new Set())
                // getMembers()
            }

    }

    const validate = ()=>{
        setErrors({})
        if (!groupNameRef.current.value){

            setErrors({
                name:"Name is required"
            })
            setOpen(false)
            return false
        }
        setOpen(false)
        return true

    }

    const handleConfirmAction= ()=>{
        console.log(currentId)
        console.log("hell handleConfirmAction function ==========><")
       if(validate()){
           if (selectedMembers.size===0){
               alert("Select One or More Members ")
               return
           }
           const membersArray = Array.isArray(selectedMembers) ? selectedMembers : Array.from(selectedMembers);

           const Group ={
               nomGroupe :groupNameRef.current.value,
               idGroupeType:currentId,
               groupeType : currentType,
               enseignantsIds :membersArray

           }

           createGroup(Group)
           setCurrentId(0)
       }

    }







    const getInitialData =  () =>{

        setError(null)
        fetch('http://localhost:8081/api/admin/groupe/initialData')
            .then(response => {
                if (!response.ok) {
                    setError('Network response was not ok ' + response.statusText+" : There was a problem with the fetch operation");
                }
                return response.json(); // Parse the JSON data from the response
            })
            .then(data => {
                console.log(data);
                setInitialData(data)// Handle the data fetched
                setLoading(false)
            })
            .catch(error => {
                setError('There was a problem with the fetch operation:', error);
                setLoading(false)
            });
    }


    useEffect(() => {
        getInitialData()

    }, []);




    const handleTypeChange = (event) => {

        if(event.target.value){
            setTypeIsChoosed(true)
        }else{
            setTypeIsChoosed(false)
        }
        if (currentType!==groupTypes[0]) {
            setMembers(event.target.value.enseignants);
            setCurrentId(currentType===groupTypes[1] ? event.target.value.idDep:event.target.value.idFil)

        }

    };


    // Define a keyframes animation for the button glow effect
    const glow = keyframes`
        0% { box-shadow: 0 0 5px #ff9800, 0 0 10px #ff9800, 0 0 20px #ff9800; }
        50% { box-shadow: 0 0 20px #ff9800, 0 0 30px #ff9800, 0 0 40px #ff9800; }
        100% { box-shadow: 0 0 5px #ff9800, 0 0 10px #ff9800, 0 0 20px #ff9800; }
    `;

    return (

        <Box sx={{display:"flex"}}>
            <Box component="main" sx={{ flexGrow: 1, p: 3 }}>
                {selectedMembers}
                <div>
                    <Grid container sx={{mb: 2}}>

                        <Grid item xs={3} sx={{
                            marginLeft: "2rem"
                        }}>
                            <TextField
                                fullWidth
                                inputRef ={groupNameRef}
                                label="Name"
                                name="Name"
                                error={!!errors.name}
                                variant="outlined"
                                margin="dense"

                            />
                            {errors.name && <Typography color="error">{errors.name}</Typography>}


                        </Grid>

                        <Grid item xs={3} sx={{
                            marginLeft: "2rem"
                        }}>
                            <FormControl fullWidth margin="dense">
                                <InputLabel>Group Types</InputLabel>
                                <Select
                                    onChange={handleGroupTypeChange}
                                    variant="outlined"
                                    label="Group Types"
                                    displayEmpty


                                >
                                    {groupTypes?.map((type,index) => (
                                        <MenuItem key={index} value={type}>
                                            {type}
                                        </MenuItem>
                                    ))}
                                </Select>
                            </FormControl>
                        </Grid>
                        {showChooseBar &&
                            <Grid item xs={3} sx={{
                                marginLeft: "2rem"
                            }}>
                                <FormControl fullWidth margin="dense">
                                    <InputLabel>{currentType===groupTypes[2]? "Sectors":"Departments"}</InputLabel>
                                    <Select
                                        onChange={handleTypeChange}
                                        variant="outlined"
                                        label={currentType===groupTypes[2]? "Sectors":"Departments"}
                                        displayEmpty
choose
                                    >

                                        {types?.map((type) => (
                                            <MenuItem key={currentType===groupTypes[1] ? type.idDep:type.idFil} value={type}>
                                                {currentType===groupTypes[1] ? type.nomDepartement:type.nomFiliere}
                                            </MenuItem>
                                        ))}
                                    </Select>
                                </FormControl>
                            </Grid>}

                    </Grid>

                    <Paper elevation={3} sx={{padding: 8, borderRadius: 7}}>
                        <Grid container sx={{mb: 2}}>
                            <Grid xs={8}>

                                <Typography variant="h6" gutterBottom>
                                    Members List
                                </Typography>
                            </Grid>


                        </Grid>

                        {loading && (
                            <Grid container justifyContent="center" alignItems="center" sx={{mt: 3, mb: 3}}>
                                <CircularProgress/>
                            </Grid>
                        )}

                        {!currentType && !loading &&

                            <Grid container justifyContent="center" alignItems="center" sx={{mt: 3, mb: 3}}>
                                <Alert severity='warning'>
                                    Choose  the group type
                                </Alert>
                            </Grid>}

                        {currentType && !loading && (
                            <>
                                <Table sx={{minWidth: 1100, borderCollapse: 'separate', borderSpacing: '0 15px'}}>
                                    <TableHead>
                                        <TableRow>

                                            <TableCell sx={{fontWeight: 'bold', color: '#555', borderBottom: 'none'}}>First
                                                Name</TableCell>
                                            <TableCell sx={{
                                                fontWeight: 'bold',
                                                color: '#555',
                                                borderBottom: 'none'
                                            }}>Last Name</TableCell>
                                            <TableCell sx={{
                                                fontWeight: 'bold',
                                                color: '#555',
                                                borderBottom: 'none'
                                            }}>Sector</TableCell>
                                            <TableCell sx={{
                                                fontWeight: 'bold',
                                                color: '#555',
                                                borderBottom: 'none'
                                            }}>Department</TableCell>
                                            <TableCell sx={{
                                                fontWeight: 'bold',
                                                color: '#555',
                                                borderBottom: 'none'
                                            }}>Subscription</TableCell>
                                            <TableCell sx={{
                                                fontWeight: 'bold',
                                                color: '#555',
                                                borderBottom: 'none'
                                            }}>Selection</TableCell>
                                        </TableRow>
                                    </TableHead>
                                    <TableBody>
                                        {members.length !== 0 &&
                                            members?.map((m, index) => (

                                                <TableRow
                                                    key={index}
                                                    sx={{
                                                        '&:hover': {
                                                            backgroundColor: '#f9f5f5',
                                                            transform: 'scale(1.001)',
                                                            transition: 'transform 1s ease',
                                                        },
                                                        borderBottom: 'none',
                                                        borderRadius: '10px',
                                                        overflow: 'hidden',
                                                        boxShadow: '0 4px 10px rgba(0, 0, 0, 0.1)',
                                                    }}
                                                >

                                                    <TableCell sx={{padding: 2, fontWeight: "bold"}}>
                                                        {m.nom}
                                                    </TableCell>

                                                    <TableCell sx={{padding: 2, fontWeight: "bold"}}>
                                                        {m.prenom}
                                                    </TableCell>

                                                    <TableCell sx={{padding: 2, fontWeight: "bold"}}>
                                                        {m.filiere.nomFiliere}
                                                    </TableCell>

                                                    <TableCell sx={{padding: 2, fontWeight: "bold"}}>
                                                        {m.departement.nomDepartement}
                                                    </TableCell>


                                                    <TableCell sx={{padding: 2, fontWeight: "bold"}}>
                                                        <Box
                                                            sx={{
                                                                backgroundColor: m.possedeUnGroupe===false ? '#ff9800' :'#4caf50' ,

                                                                color: '#fff',
                                                                borderRadius: '30px',
                                                                width:"5rem",
                                                                padding: '4px 10px',
                                                                textTransform: 'none',
                                                                animation: m.possedeUnGroupe===false ? `${glow} 2s infinite alternate`:'none' ,
                                                                cursor: 'default', // Prevent pointer cursor to indicate it's not clickable
                                                                fontSize:"10px",
                                                                textAlign:"center"
                                                            }}
                                                        >
                                                            {m.possedeUnGroupe? "Subscribed" :"Not-Yet" }
                                                        </Box>
                                                    </TableCell>



                                                    <TableCell sx={{ padding: 2, fontWeight: 'bold' }}>
                                                        <Checkbox
                                                            value={m.idPersonnel}
                                                            color="success"
                                                            checked={selectedMembers.has(m.idPersonnel)} // Checkbox checked state
                                                            onChange={() => handleCheckboxChange(m.idPersonnel)} // Handle selection
                                                        />
                                                    </TableCell>


                                                </TableRow>
                                            ))
                                        }
                                    </TableBody>
                                </Table>

                                {((currentType && currentType===groupTypes[0]) ||(currentType!==groupTypes[0] && typeIsChoosed)) && !loading && members.length === 0 &&
                                    <Grid container justifyContent="center" alignItems="center" sx={{mt: 3, mb: 3}}>
                                        <Alert severity='info'> No proposals found. </Alert>
                                    </Grid>}

                                {
                                    currentType && currentType!==groupTypes[0] && !typeIsChoosed &&
                                    <Grid container justifyContent="center" alignItems="center" sx={{mt: 3, mb: 3}}>
                                    <Alert severity='warning'>Choose the {currentType===groupTypes[2]? "sector":"department"} </Alert>
                                    </Grid>
                                }


                            </>
                        )}

                {/* Button aligned to the right */}
                <Grid container justifyContent="flex-end" sx={{ mt: 4 }}>
                    <Button
                        onClick={()=> {
                            handleGroupCreation()
                        }}
                        variant="contained"
                        startIcon={<AddCircleOutlineIcon />}
                        sx={{ minWidth: '150px' }}
                    >
                        Create Group
                    </Button>
                </Grid>

                        <DialogAndSnackBar handleConfirmAction={()=>{handleConfirmAction()}}
                                           actionType="create group"
                                           open={open}
                                           setOpen={setOpen}
                                           snackbarOpen={snackbarOpen}
                                           setSnackbarOpen={setSnackbarOpen}
                                           snackbarSeverity={snackbarSeverity}
                                           snackbarMessage={snackbarMessage}

                        />
                    </Paper>


                </div>
            </Box>
        </Box>
    );
};
export default GroupForm;