import React,{Component} from 'react';
import YouTube from 'react-youtube';

export class SessaoQuemSomos3 extends Component{
    render(){
        const opts = {
            height: '450',
            width: '100%',
            playerVars: { // https://developers.google.com/youtube/player_parameters
              autoplay: 0,
              color: '#F26B3A',
              controls: 1,
              iv_load_policy:3,
              rel:0,
              showinfo: 0
            }
          };
        return(
            <div className="container">
                <div className=" mt-5 mb-5">
                    <h1>Assitam nosso vídeo!</h1>
                </div>
                <div className="row mt-5 mb-5">
                    <div className="col-12 col-md-8 col-lg-8 mx-auto">
                        <YouTube
                            videoId="RIwA5QspxEk"
                            opts={opts}
                            onReady={this._onReady}
                        />
                    </div>
                </div>
            </div>
            
        )
    }

    _onReady(event) {
        // access to player in all event handlers via event.target
        event.target.pauseVideo();
      }
}