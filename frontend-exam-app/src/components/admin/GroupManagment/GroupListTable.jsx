import React, { useEffect, useState } from 'react';
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
    Alert, InputLabel, FormControl, Dialog, DialogContent, DialogActions, Checkbox,
} from '@mui/material';
import { useNavigate} from 'react-router-dom';
import { keyframes } from '@mui/system';
import AddIcon from '@mui/icons-material/Add';
import DeleteIcon from '@mui/icons-material/Delete';
import IconButton from "@mui/material/IconButton";
import DialogAndSnackBar from "../../utils/DialogAndSnackBar";
import PersonIcon from "@mui/icons-material/Person";
import Box from "@mui/material/Box";



function GroupListTable(){
    const [groups ,setGroups]  = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [open,setOpen] =useState(false)
    const [membersOpenDialg,setMembersOpenDialg]= useState(false);
    const [actionType,setActionType] = useState()
    const [snackbarOpen, setSnackbarOpen] = useState(false); // Snackbar state
    const [snackbarMessage, setSnackbarMessage] = useState(''); // Snackbar message
    const [snackbarSeverity, setSnackbarSeverity] = useState('success'); // Snackbar severity
    const [currentPersonnel,setCurrentPersonnel] =useState();
    const [currentGroup,setCurrentGroup] =useState();
    const [members, setMembers] =useState([]);
    const navigate  =useNavigate();
    const [selectedMembers, setSelectedMembers] = useState(new Set()); // Store selected member IDs




    const handleClickOpen = (action,personnel) => {

        setActionType(action);
        setOpen(true);
        setCurrentPersonnel(personnel);
        console.log(personnel);
    }

    const handleMemberDeletion = async (personnel) => {
        try {
            const response = await fetch(`http://localhost:8081/api/admin/groupe/delete/${currentGroup.idGroupe}/member/${personnel.idPersonnel}`, {
                method: 'DELETE',
            });
            if (response.ok) {
                // Handle success (e.g., refresh data or show a success message)
                const message = await response.text();
                setSnackbarSeverity("success")
                setSnackbarMessage(message);
                setOpen(false);
                setSnackbarOpen(true);
                getGroups()

            } else {
                setSnackbarSeverity("error")
                setSnackbarMessage(`Failed to delete Personnel ${personnel.prenom}!`);
                setOpen(false);
                setSnackbarOpen(true);
            }
        } catch (error) {
            // Handle error
            setSnackbarSeverity("error")
            setSnackbarMessage(`Error occurred during deletion of Personnel ${personnel.nom} ${personnel.prenom} from the group!`);
            setSnackbarOpen(true);
            console.error('Error deleting personnel:', error);
        }

    }



    const handleClose = () => {
        setMembersOpenDialg(false);
        setSelectedMembers(new Set())
    };

    const handleAddMember  = ()=>{

        setMembersOpenDialg(true)
        getMembers()
    }


    const getMembers  = async ()=>{

        try {
            const response = await fetch(`http://localhost:8081/api/admin/groupe/getEnseignants/${currentGroup.groupeType}/${currentGroup.idGroupeType}`, {
                method: 'GET',
            });
            if (response.ok) {
                // Handle success (e.g., refresh data or show a success message)
                setMembers( await response.json());

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
            console.error('Error:', error);
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


    const addMembers = async ()=>{
        if (selectedMembers.size===0){
            alert("Select One or More Members ")
        }else {
            try {
                // Convert Set to Array if `selectedMembers` is a Set
                const membersArray = Array.isArray(selectedMembers) ? selectedMembers : Array.from(selectedMembers);

                const response = await fetch(`http://localhost:8081/api/admin/groupe/addEnseignants/${currentGroup.idGroupe}`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify({
                        enseignants: membersArray, // Ensure it's an array
                    })
                });

                console.log(membersArray)

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
                setMembersOpenDialg(false)
                getMembers()
            }
        }
    }


    const handleGroupDeletion =async()=>{


            try {

                const response = await fetch(`http://localhost:8081/api/admin/groupe/delete/${currentGroup.idGroupe}`, {
                    method: 'DELETE',

                });


                if (response.ok) {

                    const message = await response.text()
                    setSnackbarSeverity("success")
                    setSnackbarMessage(message);
                    setOpen(false);
                    setSnackbarOpen(true);

                } else {
                    setSnackbarSeverity("error")
                    setSnackbarMessage(`Failed to delete the Group`);
                    setOpen(false);
                    setSnackbarOpen(true);
                }
            } catch (error) {
                // Handle error
                setSnackbarSeverity("error")
                setSnackbarMessage(`Error occurred during deletion of the group!`);
                setSnackbarOpen(true);
            }finally {
                navigate("/Group")
            }
    }
    const handleConfirmAction= (personnel)=>{
        //TODO
        console.log(actionType);

        if (actionType==='delete member'){

            handleMemberDeletion(personnel)

        }else if (actionType==='delete group'){
            handleGroupDeletion()
        }else{
            handleAddMember()
        }

    }







    const getGroups =  () =>{
        setError(null)
        fetch('http://localhost:8081/api/admin/groupe/list')
            .then(response => {
                if (!response.ok) {
                    setError('Network response was not ok ' + response.statusText+" : There was a problem with the fetch operation");
                }
                return response.json(); // Parse the JSON data from the response
            })
            .then(data => {
                console.log(data);
                setGroups(data)// Handle the data fetched
                setLoading(false)
            })
            .catch(error => {
                setError('There was a problem with the fetch operation:', error);
                setLoading(false)
            });
    }


    useEffect(() => {
        getGroups()

    }, []);




    const handleFilterChange = (event) => {
        setCurrentGroup(event.target.value);

    };


    // Define a keyframes animation for the button glow effect
    const glow = keyframes`
        0% { box-shadow: 0 0 5px #ff9800, 0 0 10px #ff9800, 0 0 20px #ff9800; }
        50% { box-shadow: 0 0 20px #ff9800, 0 0 30px #ff9800, 0 0 40px #ff9800; }
        100% { box-shadow: 0 0 5px #ff9800, 0 0 10px #ff9800, 0 0 20px #ff9800; }
    `;

    return (
        <div><> <Dialog
            open={membersOpenDialg}
            onClose={handleClose}
            aria-labelledby="alert-dialog-title"
            aria-describedby="alert-dialog-description"

        >

            <DialogContent>

                <Table sx={{minWidth: 300, borderCollapse: 'separate', borderSpacing: '0 15px'}}>
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
                            }}>Sector
                            </TableCell>
                            <TableCell sx={{
                                fontWeight: 'bold',
                                color: '#555',
                                borderBottom: 'none'
                            }}>Department
                            </TableCell>

                            <TableCell sx={{
                                fontWeight: 'bold',
                                color: '#555',
                                borderBottom: 'none'
                            }}>subscribed
                            </TableCell>
                            <TableCell sx={{
                                fontWeight: 'bold',
                                color: '#555',
                                borderBottom: 'none'
                            }}>Add</TableCell>

                        </TableRow>
                    </TableHead>
                    <TableBody>





                            {members.map((m,index)=>(

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
                                )


                            )}




                    </TableBody>
                </Table>
            </DialogContent>
            <DialogActions>
                <Button onClick={handleClose} color="primary">
                    Cancel
                </Button>
                <Button onClick={addMembers} color="primary" autoFocus>
                    Add
                </Button>
            </DialogActions>
        </Dialog>

        </>
            <Grid container sx={{mb: 2}}>
                <Grid item xs ={10}>
                    <FormControl fullWidth margin="dense">
                        <InputLabel>Groups</InputLabel>
                    <Select
                        value={currentGroup?.nomGroupe}
                        onChange={handleFilterChange}
                        variant="outlined"
                        label="Groups"
                        displayEmpty

                        sx={{
                            width: '600px',
                            borderRadius: '50px',
                            backgroundColor: '#f0f0f0',
                            textAlign:"center",
                            '& .MuiOutlinedInput-root': {
                                borderRadius: '50px',
                            },
                            '& .MuiOutlinedInput-input': {
                                padding: '10px 14px',
                            },
                        }}
                    >
                        {groups?.map((group) => (
                            <MenuItem key={group.idGroupe} value={group}>
                                {group?.nomGroupe} <span  style={{color: "red", fontSize:"10px"}}>{`   -${group?.groupeType==="aleatoire" ? "General Group":group?.groupeType==="fil"? "Sector Group":"Department Group"}-`}</span>
                            </MenuItem>
                        ))}
                    </Select>
                        </FormControl>
                </Grid>



               <Grid item>

                   <Button variant="contained" startIcon={<AddIcon/>} onClick={()=>navigate("Form")} >
                       Add Group
                   </Button>

               </Grid>

            </Grid>

            <Paper elevation={3} sx={{padding: 8, borderRadius: 7}}>
              <Grid container sx={{mb: 2}}>
                  <Grid xs={8}>

                      <Typography variant="h6" gutterBottom>
                          Members List
                      </Typography>
                  </Grid>
                  <Grid xs={2}>
                      <Button variant="outlined" startIcon={<PersonIcon/>}  sx={{backgroundColor :"transparent",color:"green",
                          borderColor:"#dcebdc",
                      "&:hover":{
                          backgroundColor:"#dcebdc"
                      }}}
                      onClick={handleAddMember}
                      >
                          Add Member
                      </Button>
                  </Grid>
                  <Grid xs={2}>

                  <Button variant="outlined" startIcon={<DeleteIcon />} sx={{color:"red",backgroundColor:"transparent",borderColor:"#f8e4e4",
                          "&:hover":{


                      backgroundColor:"#f8e4e4"
                  }}}
                  onClick={()=>handleClickOpen("delete group",null)}
                  >
                      Delete Group
                  </Button>
                  </Grid>

              </Grid>

                {loading && (
                    <Grid container justifyContent="center" alignItems="center" sx={{mt: 3, mb: 3}}>
                        <CircularProgress/>
                    </Grid>
                )}

                {!currentGroup && !loading &&

                    <Grid container justifyContent="center" alignItems="center" sx={{mt: 3, mb: 3}}>
                        <Alert severity= 'warning'>
                            Choose a groupe
                        </Alert>
                    </Grid>}

                {currentGroup && !loading &&(
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
                                    }}>Actions</TableCell>
                                </TableRow>
                            </TableHead>
                            <TableBody>

                                { currentGroup.enseignants?.length!==0 &&
                                currentGroup.enseignants?.map((e, index) => (

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
                                                {e.nom}
                                            </TableCell>

                                            <TableCell sx={{padding: 2, fontWeight: "bold"}}>
                                                {e.prenom}
                                            </TableCell>

                                            <TableCell sx={{padding: 2, fontWeight: "bold"}}>
                                                {e.filiere.nomFiliere}
                                            </TableCell>

                                            <TableCell sx={{padding: 2, fontWeight: "bold"}}>
                                                {e.departement.nomDepartement}
                                            </TableCell>



                                            <TableCell sx={{padding: 2, fontWeight: "bold"}}>

                                                <IconButton color="error" onClick={()=>handleClickOpen("delete member",e)}>
                                                    <DeleteIcon/>
                                                </IconButton>

                                            </TableCell>


                                        </TableRow>
                            ))
                            }
                            </TableBody>
                        </Table>

                        {currentGroup && !loading && currentGroup.enseignants?.length===0 &&
                             <Grid container justifyContent="center" alignItems="center" sx={{mt: 3, mb: 3}}>
                    <Alert severity='info' >Empty Group </Alert>
                    </Grid>}

                        <DialogAndSnackBar handleConfirmAction={()=>handleConfirmAction(currentPersonnel)}
                                           actionType={actionType}
                                           open={open}
                                           setOpen={setOpen}
                                           snackbarOpen={snackbarOpen}
                                           setSnackbarOpen={setSnackbarOpen}
                                           snackbarSeverity={snackbarSeverity}
                                           snackbarMessage={snackbarMessage}

                        />
                    </>
                )}
            </Paper>
        </div>
    );
};
export default GroupListTable;