/*
 * Copyright (c) 2015 The Jupiter Project
 *
 * Licensed under the Apache License, version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jupiter.transport.error;

import org.jupiter.common.util.internal.logging.InternalLogger;
import org.jupiter.common.util.internal.logging.InternalLoggerFactory;
import org.jupiter.rpc.channel.JChannel;

/**
 * jupiter
 * org.jupiter.transport.error
 *
 * @author jiachun.fjc
 */
@SuppressWarnings("all")
public class Signals {

    private static final InternalLogger logger = InternalLoggerFactory.getInstance(Signals.class);

    /** 错误的MAGIC */
    public static final Signal ILLEGAL_MAGIC    = Signal.valueOf(Signals.class, "ILLEGAL_MAGIC");
    /** 错误的消息标志位 */
    public static final Signal ILLEGAL_SIGN     = Signal.valueOf(Signals.class, "ILLEGAL_SIGN");
    /** Read idel 链路检测 */
    public static final Signal READER_IDLE      = Signal.valueOf(Signals.class, "READER_IDLE");
    /** Protocol body 太大 */
    public static final Signal BODY_TOO_LARAGE  = Signal.valueOf(Signals.class, "BODY_TOO_LARAGE");

    public static void handleSignal(Signal signal, JChannel channel) {
        if (signal == ILLEGAL_MAGIC) {
            logger.error("Illegal protocol magic on {}, will force to close this channel.", channel);
        } else if (signal == ILLEGAL_SIGN) {
            logger.error("Illegal protocol sign on {}, will force to close this channel.", channel);
        } else if (signal == READER_IDLE) {
            logger.error("Read timeout on {}, will force to close this channel.", channel);
        } else if (signal == BODY_TOO_LARAGE) {
            logger.error("Protocol body is too larage on {}, will force to close this channel.", channel);
        } else {
            logger.error("Unknow signal on {}, will force to close this channel.", channel);
        }
        channel.close();
    }
}
