package com.company;
import java.net.URL;
import com.vmware.vim25.*;
import com.vmware.vim25.mo.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

public class Main {

    public static void main(String[] args) throws Exception {
        //estabalish the connection
        System.out.println("CMPE281 HW2 from Danhua Yang");
        ServiceInstance si = new ServiceInstance(new URL("https://130.65.159.14/sdk"), "cmpe281_sec3_student@vsphere.local", "cmpe-LXKN", true);
        System.out.println("DanhuaYang-681");
        Folder rootFolder = si.getRootFolder();
        ManagedEntity[] mes = new InventoryNavigator(rootFolder).searchManagedEntities("VirtualMachine");
        ManagedEntity[] hostmanagedEntities = new InventoryNavigator(rootFolder).searchManagedEntities("HostSystem");
        //read command
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while (true) {
            try {
                str = input.readLine().toLowerCase();
                String[] cmd = str.split(" ");
                if (cmd.length <= 1) { //for host, exit, help, vm commands
                    switch (cmd[0]) {
                        case "exit":
                            si.getServerConnection().logout();
                            return;
                        case "help":
                            System.out.println("usage:");
                            String leftAlignFormat = " %-20s  %-4s  %n";
                            System.out.format(leftAlignFormat, "exit", "exit the program");
                            System.out.format(leftAlignFormat, "host", "enumerate hosts");
                            System.out.format(leftAlignFormat, "host name info", "show info for hname");
                            System.out.format(leftAlignFormat, "host hname datastore", "enumerate datastores for hname");
                            System.out.format(leftAlignFormat, "host hname network", "enumerate networks for hname");
                            System.out.format(leftAlignFormat, "vm", "enumerate vms");
                            System.out.format(leftAlignFormat, "vm vname info", "show info for vname");
                            System.out.format(leftAlignFormat, "vm vname shutdown", "shutdown OS on vname");
                            System.out.format(leftAlignFormat, "vm vname on", "power on vname");
                            System.out.format(leftAlignFormat, "vm vname off", "power off vname");
                            break;
                        case "host":
                            if (hostmanagedEntities == null || hostmanagedEntities.length == 0) {
                                System.out.println("No host instance found");
                            }
                            for (int i = 0; i < hostmanagedEntities.length; i++) {
                                HostSystem host = (HostSystem) hostmanagedEntities[i];
                                System.out.println("host:[" + i + "]:" + " Name = " + host.getName());
                            }
                            break;
                        case "vm":
                            if (mes == null || mes.length == 0) {
                                System.out.println("No vm instance found");
                            }
                            for (int i = 0; i < mes.length; i++) {
                                VirtualMachine vm = (VirtualMachine) mes[i];
                                System.out.println("vm[" + i + "]: Name = " + vm.getName());
                            }
                            break;
                        default:
                            System.out.println("Please enter valid command. type 'help' for details");
                            break;
                    }
                } else if(cmd[0].equals("host")){ // for command related to host
                    if (hostmanagedEntities == null || hostmanagedEntities.length == 0) {
                        System.out.println("No host instance found");
                    }
                    HostSystem thisHost = (HostSystem) new InventoryNavigator(rootFolder).searchManagedEntity("HostSystem", cmd[1]);
                    if(thisHost == null){
                        System.out.println("Please enter correct host name");
                    }
                    switch (cmd[2]){
                        case "info":
                            System.out.println("Name = " + thisHost.getName());
                            System.out.println("ProductFullName = " + thisHost.getConfig().getProduct().getFullName());
                            System.out.println("Cpu cores = " + thisHost.getHardware().getCpuInfo().getNumCpuCores());
                            System.out.println("RAM = " + thisHost.getHardware().getMemorySize()/1024/1024/1024 + " GB");
                            break;
                        case "datastore":
                            System.out.println("Name = " + thisHost.getName());
                            HostDatastoreBrowser hdb = thisHost.getDatastoreBrowser();
                            Datastore[] allDS = hdb.getDatastores();
                            for(int i=0; i<allDS.length; i++){
                                DatastoreInfo dInfo = allDS[i].getInfo();
                                System.out.println("Datastore[" + i +"]: name = " + dInfo.getName() + ", capacity = "+ dInfo.getMaxVirtualDiskCapacity()/1024/1024/1024 +" GB" + ", FreeSpace = "+dInfo.getFreeSpace()/1024/1024/1024 + " GB");
                            }
                            break;
                        case "network":
                            System.out.println("Name = " + thisHost.getName());
                            Network[] networks = thisHost.getNetworks();
                            for(int i=0; i<networks.length; i++){
                                System.out.println("Network["+i+"]: name = "+networks[i].getName());
                            }
                            break;
                        default:
                            System.out.println("Please enter valid command. type 'help' for details");
                            break;
                    }
                }else if(cmd[0].equals("vm")){ //for command related to vm
                    if (mes == null || mes.length == 0) {
                        System.out.println("No vm instance found");
                    }
                    VirtualMachine thisVM = (VirtualMachine) new InventoryNavigator(rootFolder).searchManagedEntity("VirtualMachine", cmd[1]);
                    if(thisVM == null){
                        System.out.println("Please enter correct vm name");
                    }
                    switch(cmd[2]){
                        case "info":
                            VirtualMachineConfigInfo vminfo = thisVM.getConfig();
                            System.out.println("Name = " + thisVM.getName());
                            System.out.println("Guest full name = " + vminfo.getGuestFullName());
                            System.out.println("Guest state = " + thisVM.getGuest().getGuestState());
                            System.out.println("IP addr = " + thisVM.getGuest().getIpAddress());
                            System.out.println("Tool running status = " + thisVM.getGuest().toolsRunningStatus);
                            System.out.println("Power state = " + thisVM.getSummary().getRuntime().powerState);
                            break;
                        case "on":
                            System.out.println("Name = " + thisVM.getName());
                            Task taskON = thisVM.powerOnVM_Task(null);
                            System.out.println("waiting...");
                            taskON.waitForTask();
                            TaskInfoState stateON = taskON.getTaskInfo().getState();
                            if(stateON == taskON.getTaskInfo().getState().success){
                                System.out.println("Power on VM: status = " + stateON.toString() +", completion time = " + taskON.getTaskInfo().getCompleteTime().getTime());

                            }else{
                                System.out.println("Power on VM: status = " + taskON.getTaskInfo().getError().getLocalizedMessage() +", completion time = " + taskON.getTaskInfo().getCompleteTime().getTime());
                            }
                            break;
                        case "off":
                            System.out.println("Name = " + thisVM.getName());
                            Task taskOFF = thisVM.powerOffVM_Task();
                            System.out.println("waiting...");
                            taskOFF.waitForTask();
                            TaskInfoState state = taskOFF.getTaskInfo().getState();
                            if(state == taskOFF.getTaskInfo().getState().success){
                                System.out.println("Power offVM: status = " + state.toString() +", completion time = " + taskOFF.getTaskInfo().getCompleteTime().getTime());
                            }else{
                                System.out.println("Power offVM: status = "+ taskOFF.getTaskInfo().getError().getLocalizedMessage() + ", completion time = " + taskOFF.getTaskInfo().getCompleteTime().getTime());
                            }
                            break;
                        case "shutdown":
                            System.out.println("Name = " + thisVM.getName());
                            try {
                                thisVM.shutdownGuest();
                                System.out.println("waiting...");
                                Timer timer = new Timer();
                                TimerTask task = new TimerTask() {
                                    @Override
                                    public void run(){
                                        if(thisVM.getGuest().getGuestState().equals("notRunning")){ //stop the timer until its shutdown
                                            //System.out.println(thisVM.getGuest().getGuestState());
                                            System.out.println("Shutdown guest: completed, time = " + LocalDateTime.now());
                                            timer.cancel();
                                            timer.purge();
                                        }
                                    }
                                };
                                timer.schedule(task,0,2000);//check guest state every 2 seconds
                            }catch(Exception e){
                                System.out.println("Graceful shutdown failed. Now try a hard power off...");
                                Task taskHardOFF = thisVM.powerOffVM_Task();
                                System.out.println("waiting...");
                                taskHardOFF.waitForTask();
                                TaskInfoState hardState = taskHardOFF.getTaskInfo().getState();
                                if(hardState == taskHardOFF.getTaskInfo().getState().success){
                                    System.out.println("Power offVM: status = " + hardState.toString() +", completion time = " + taskHardOFF.getTaskInfo().getCompleteTime().getTime());
                                }else{
                                    System.out.println("Power offVM: status = "+ taskHardOFF.getTaskInfo().getError().getLocalizedMessage() + ", completion time = " + taskHardOFF.getTaskInfo().getCompleteTime().getTime());
                                }
                            }
                            break;
                        default:
                            System.out.println("Please enter valid command. type 'help' for details");
                            break;
                    }
                }else{ //for error handling
                    System.out.println("Please enter valid command. type 'help' for details");
                }
            }
            catch (Exception e) {
                System.out.println("Server error or invalid command. Please try to enter valid command. type 'help' for details");
            }
        }
    }
}
