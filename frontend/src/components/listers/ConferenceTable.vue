<template>
    <div>
        <v-data-table
                :headers="headers"
                :items="values"
                :items-per-page="5"
                class="elevation-1"
        ></v-data-table>

        <v-col style="margin-bottom:40px;">
            <div class="text-center">
                <v-dialog
                        v-model="openDialog"
                        width="332.5"
                        fullscreen
                        hide-overlay
                        transition="dialog-bottom-transition"
                >
                    <template v-slot:activator="{ on, attrs }">
                        <v-fab-transition>
                            <v-btn
                                    color="blue"
                                    fab
                                    dark
                                    large
                                    style="position:absolute; bottom: 5%; right: 2%; z-index:99"
                                    @click="openDialog=true;"
                            >
                                <v-icon v-bind="attrs" v-on="on">mdi-plus</v-icon>
                            </v-btn>
                        </v-fab-transition>
                    </template>

                    <Conference :offline="offline" class="video-card" :isNew="true" :editMode="true" v-model="newValue" @add="append" v-if="tick"/>
                
                    <v-btn
                            style="postition:absolute; top:2%; right:2%"
                            @click="closeDialog()"
                            depressed 
                            icon 
                            absolute
                    >
                        <v-icon>mdi-close</v-icon>
                    </v-btn>
                </v-dialog>
            </div>
        </v-col>
    </div>
</template>

<script>
    const axios = require('axios').default;
    import Conference from './../Conference.vue';

    export default {
        name: 'ConferenceManager',
        components: {
            Conference,
        },
        props: {
            offline: Boolean,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            values: [],
            headers: 
                [
                    { text: "reserveId", value: "reserveId" },
                    { text: "reserveStartTime", value: "reserveStartTime" },
                    { text: "reserveEndTime", value: "reserveEndTime" },
                    { text: "reserveName", value: "reserveName" },
                    { text: "roomId", value: "roomId" },
                    { text: "hostId", value: "hostId" },
                    { text: "userId1", value: "userId1" },
                    { text: "userId2", value: "userId2" },
                    { text: "userId3", value: "userId3" },
                    { text: "userId4", value: "userId4" },
                    { text: "userId5", value: "userId5" },
                ],
            conference : [],
            newValue: {},
            tick : true,
            openDialog : false,
        }),
        async created() {
            if(this.offline){
                if(!this.values) this.values = [];
                return;
            }

            var temp = await axios.get(axios.fixUrl('/conferences'))
            temp.data._embedded.conferences.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])
            this.values = temp.data._embedded.conferences;

            this.newValue = {
                'reserveId': 0,
                'reserveStartTime': '2022-09-02',
                'reserveEndTime': '2022-09-02',
                'reserveName': '',
                'roomId': 0,
                'hostId': 0,
                'userId1': 0,
                'userId2': 0,
                'userId3': 0,
                'userId4': 0,
                'userId5': 0,
            }
        },
        methods: {
            closeDialog(){
                this.openDialog = false
            },
            append(value){
                this.tick = false
                this.newValue = {}
                this.values.push(value)
                
                this.$emit('input', this.values);

                this.$nextTick(function(){
                    this.tick=true
                })
            },
        }
    }
</script>

