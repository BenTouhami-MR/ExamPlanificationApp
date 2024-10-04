import * as React from 'react';
import Box from '@mui/material/Box';
import Tabs from '@mui/material/Tabs';
import Tab from '@mui/material/Tab';

export default function CenteredTabs({onPersnnelType}) {
    const [value, setValue] = React.useState(1); // Default value set to 1 (Administrator)

    const handleChange = (event, newValue) => {
        // Map the tab index to the desired values
        if (newValue === 0) {
            setValue(1); // Administrator should map to value 1
            onPersnnelType(1);
        } else if (newValue === 1) {
            setValue(2); // Teacher should map to value 2
            onPersnnelType(2);
        }

    };

    return (
        <Box sx={{ width: '100%', bgcolor: 'background.paper' }}>
            <Tabs
                value={value === 1 ? 0 : 1} // Map value back to tab index for display purposes
                onChange={handleChange}
                centered
                sx={{
                    '& .MuiTabs-indicator': {
                        backgroundColor: '#FFD700', // Light gold for the indicator
                    },
                }}
            >
                <Tab
                    label="Administrator"
                    sx={{
                        color: '#c7ba9a',
                        '&.Mui-selected': {
                            color: '#b2af9c',
                        },
                    }}
                />
                <Tab
                    label="Teacher"
                    sx={{
                        color: '#c7ba9a',
                        '&.Mui-selected': {
                            color: '#b2af9c',
                        },
                    }}
                />
            </Tabs>
        </Box>
    );
}
